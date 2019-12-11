package com.example.lottery.domain;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.example.lottery.inject.RandomNumber;

@Named("lottery")
@RequestScoped
public class LotteryNumber {
	@RandomNumber(min=100,max=1500,size = 20,sorted = false)
	private Collection<Integer> numbers;

	@PostConstruct
	public void init() {
		System.out.println("init(): " + numbers);
	}

	@PreDestroy
	public void destroy() {
		System.out.println("destroy(): " + numbers);
	}

	public LotteryNumber() {
	}

	public Collection<Integer> getNumbers() {
		return numbers;
	}

}
