package com.example.lottery.cdi.extention;

import java.lang.reflect.Field;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import com.example.lottery.inject.RandomNumber;

public class LotteryNumberCdiExtension implements Extension {
	public <T> void initializeRandomNumberExtension(@Observes ProcessInjectionTarget<T> pit) {
		AnnotatedType<T> annotatedType = pit.getAnnotatedType();
		Field[] fields = annotatedType.getJavaClass().getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				pit.setInjectionTarget(new RandomNumberInjectionTarget<T>(pit.getInjectionTarget()));
			}
		}
	}
}
