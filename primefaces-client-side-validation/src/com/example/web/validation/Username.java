package com.example.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import org.primefaces.validate.bean.ClientConstraint;

/**
 *
 * @author Binnur Kurt (binnur.kurt@gmail.com)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Pattern.List({
    @Pattern(regexp = "^[a-zA-Z].*$", message = "{validation.username1}"),
    @Pattern(regexp = "^[a-zA-z0-9]{5,}$", message = "{validation.username2}")
})
@ClientConstraint(resolvedBy=UsernameClientValidator.class)
@Constraint(validatedBy = {})
public @interface Username {

    String message() default "{validation.username3}";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};

}
