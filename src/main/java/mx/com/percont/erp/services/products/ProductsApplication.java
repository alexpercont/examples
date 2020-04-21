package mx.com.percont.erp.services.products;

import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
@SwaggerDefinition(
        basePath = "",
        info = @Info(title = "Percont ERP Products REST API", version = "1.0"))
public class ProductsApplication extends Application {

}
