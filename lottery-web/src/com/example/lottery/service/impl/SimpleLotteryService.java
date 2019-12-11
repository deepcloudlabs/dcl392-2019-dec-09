package com.example.lottery.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.example.lottery.aspect.AuditInterceptor;
import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
//@Named
//@Singleton
//@Singleton
@Stateless
//@Audit
@Interceptors({ AuditInterceptor.class })
public class SimpleLotteryService implements LotteryService {

	@Inject
	private RandomNumberService randomNumberSrv;

	@Override
	public List<List<Integer>> draw(int n) {
		return IntStream.range(0, n).mapToObj(i -> this.draw()).collect(Collectors.toList());
	}

	@Override
	public List<Integer> draw() {
		return IntStream.generate(() -> randomNumberSrv.generate(1, 50)).distinct().limit(6).sorted().boxed()
				.collect(Collectors.toList());
	}

}
