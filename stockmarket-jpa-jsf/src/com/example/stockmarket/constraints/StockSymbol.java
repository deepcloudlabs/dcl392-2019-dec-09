package com.example.stockmarket.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Pattern(regexp = "^[A-Z]{3,5}$",message = "You must provide a valid symbol!")
public @interface StockSymbol {
	String message() default "You must provide a valid symbol!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
