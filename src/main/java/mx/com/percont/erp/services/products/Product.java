package mx.com.percont.erp.services.products;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(namespace = "http://percont.com.mx/erp")
@ApiModel("product")
public class Product {

    @ApiModelProperty(required = true,
            name = "id", value = "Product identifier")
    @NotNull @NotEmpty
    private String id;

    @ApiModelProperty(required = true,
            name = "name", value = "Name of the product")
    @NotNull @NotEmpty
    private String name;

    @ApiModelProperty(required = true,
            name = "unit", value = "Unit of the product package (i.e: ml, l, g, kg)")
    @NotNull @NotEmpty
    private String unit;

    @ApiModelProperty(required = true,
            name = "contents", value = "Amount of units contained in the product, greater than 0")
    @NotNull
    @Min(value = 0)
    private double contents;

    @ApiModelProperty(required = true,
            name = "price", value = "Price of product, greather than 0")
    @NotNull
    @Min(value = 0)
    private double price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getContents() {
        return contents;
    }

    public void setContents(double contents) {
        this.contents = contents;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
