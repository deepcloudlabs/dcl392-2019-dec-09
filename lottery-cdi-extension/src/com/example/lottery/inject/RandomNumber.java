package com.example.lottery.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RandomNumber {

	int min() default 1;

	int max() default 49;

	int size() default 6;

	boolean distinct() default true;

	boolean sorted() default true;
	
}
