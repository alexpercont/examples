package mx.com.percont.erp.services.products;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.ws.rs.core.Response.ResponseBuilder;
import static javax.ws.rs.core.Response.Status;
import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.ok;

@Path("/products")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Api(value = "/products")
@Stateless
public class ProductsEndpoint {

    @PersistenceContext(unitName = "products_service_pu")
    private EntityManager entityManager;

    private static Product apply(@NotNull ProductEntity entity) {
        Product pr = new Product();
        pr.setId(entity.getId());
        pr.setContents(entity.getContents());
        pr.setName(entity.getName());
        pr.setPrice(entity.getPrice());
        pr.setUnit(entity.getUnit());
        return pr;
    }

    private static ProductEntity applyBack(@NotNull Product product){
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setContents(product.getContents());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setUnit(product.getUnit());
        return entity;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Retrieve all products")
    @ApiResponses({
            @ApiResponse(code = 200, response = Product.class, responseContainer = "List", message = "Ok"),
            @ApiResponse(code = 404, message = "Bad request")
    })
    public Response getProducts(
            @ApiParam(value = "Name of the product to search (case insensitive)",
                      name = "name", type = "string") @QueryParam("name") String name,
            @ApiParam(value = "Filters products by price (greater than, exclusive)",
                      name = "price_greater_than", type = "number") @QueryParam("price_greater_than") Double priceGreaterThan,
            @ApiParam(value = "Filters products by price (less than, inclusive)",
                      name = "price_less_than", type = "number") @QueryParam("price_less_than") Double priceLowerThan){
        CriteriaBuilder builder =  this.entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> root = query.from(ProductEntity.class);
        if (null != name){
            query.where(builder.like(root.get("name"), "%".concat(name).concat("%")));
        }

        // Intentional defect: Documentation says fiter is inclusive, code says otherwise.
        if (null != priceLowerThan) {
            query.where(builder.lessThan(root.get("price"), priceLowerThan));
        }

        if (null != priceGreaterThan) {
            query.where(builder.greaterThan(root.get("price"), priceGreaterThan));
        }
        Stream<ProductEntity> stream = entityManager.createQuery(query).getResultStream();
        List<Product> productList = stream.map(ProductsEndpoint::apply).collect(Collectors.toList());
        return ok(productList.toArray(new Product[productList.size()])).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    @ApiOperation(value = "Obtain the details of a single product.", response = Product.class)
    @ApiResponses({
            @ApiResponse(code = 200, response = Product.class, message = "Ok"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public Response getProduct(@ApiParam("Id of the product to find") @PathParam("id") String id) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        ResponseBuilder builder = null;
        if (null != productEntity){
            builder = Response.ok(apply(productEntity));
        } else {
            builder = Response.status(Status.NOT_FOUND);
        }
        return builder.build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @ApiOperation(value = "Register a new product.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public Response createProduct(@Context UriInfo uriInfo,
                                  @ApiParam("Details of the new product")
                                  @NotNull @Valid Product product) {
        ProductEntity entity = applyBack(product);
        entityManager.persist(entity);
        return created(uriInfo.getAbsolutePathBuilder().path(entity.getId()).build()).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{id}")
    @ApiOperation(value = "Update the details of a product.")
    @ApiResponses({
            @ApiResponse(code = 200, response = Product.class, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not found")
    })
    public Response updateProduct(
            @ApiParam("Id of the product to modify") @PathParam("id") String id,
            @ApiParam("Details of the product to update") @NotNull @Valid Product product) {
        ProductEntity entity = entityManager.find(ProductEntity.class, id);
        if (null == entity){
            return Response.status(Status.NOT_FOUND).build();
        }

        ProductEntity newEntity = applyBack(product);
        ProductEntity updated = entityManager.merge(newEntity);
        return Response.ok(apply(updated)).build();
    }
}
