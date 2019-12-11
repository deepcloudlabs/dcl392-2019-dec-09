package com.example.lottery.cdi.extention;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;

import com.example.lottery.inject.RandomNumber;

public class RandomNumberInjectionTarget<T> implements InjectionTarget<T> {
	private final InjectionTarget<T> injectionTarget;

	public RandomNumberInjectionTarget(InjectionTarget<T> injectionTarget) {
		this.injectionTarget = injectionTarget;
	}

	@Override
	public void dispose(T instance) {
		injectionTarget.dispose(instance);
	}

	@Override
	public Set<InjectionPoint> getInjectionPoints() {
		return injectionTarget.getInjectionPoints();
	}

	@Override
	public T produce(CreationalContext<T> ctx) {
		return injectionTarget.produce(ctx);
	}

	@Override
	public void inject(T instance, CreationalContext<T> ctx) {
		setRandomNumber(instance);
	}

	private void setRandomNumber(T instance) {
		Class<?> clazz = instance.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(RandomNumber.class)) {
				RandomNumber rn = field.getAnnotation(RandomNumber.class);
				int min = rn.min();
				int max = rn.max();
				int size = rn.size();
				Collection<Integer> numbers = new Random().ints(min, max).distinct().limit(size).sorted().boxed()
						.collect(Collectors.toList());
				field.setAccessible(true);
				try {
					field.set(instance, numbers);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				field.setAccessible(false);
			}
		}

	}

	@Override
	public void postConstruct(T instance) {
		injectionTarget.postConstruct(instance);
	}

	@Override
	public void preDestroy(T instance) {
		injectionTarget.preDestroy(instance);
	}

}
