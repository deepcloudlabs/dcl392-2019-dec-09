package com.example.web.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import org.primefaces.validate.bean.ClientConstraint;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp="^\\+?[a-z0-9](([-+.]|[_]+)?[a-z0-9]+)*@([a-z0-9]+(\\.|\\-))+[a-z]{2,6}$",
message="{validation.email}")
@ClientConstraint(resolvedBy=EmailClientValidator.class)
@Constraint(validatedBy={})
public @interface Email {
	String message() default "{validation.email}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
