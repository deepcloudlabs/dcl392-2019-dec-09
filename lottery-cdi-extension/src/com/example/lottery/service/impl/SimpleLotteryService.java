package com.example.lottery.service.impl;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.Stateless;

import com.example.lottery.service.LotteryService;

@Stateless
public class SimpleLotteryService implements LotteryService {

	@Override
	public Collection<Integer> draw() {
		return draw(1,49,6,true);
	}

	@Override
	public Collection<Integer> draw(int min, int max, int size, boolean sorted) {
		Stream<Integer> rands= 
		         new Random()		         
		        .ints(min, max)
				.boxed()
				.distinct()
				.limit(size);
		if (sorted)
			rands= rands.sorted();
		return	rands.collect(Collectors.toList());
	}

}
