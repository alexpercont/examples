package mx.com.percont.erp.services.products;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(PARAMETER)
@Documented
@Constraint(validatedBy = {})
public @interface ValidProduct {

    String message() default "Invalid product";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
