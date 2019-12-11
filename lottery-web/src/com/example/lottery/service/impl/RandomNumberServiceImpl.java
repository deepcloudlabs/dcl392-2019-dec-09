package com.example.lottery.service.impl;

import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.aspect.Audit;
import com.example.lottery.service.RandomNumberService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named
@Singleton
@Audit
public class RandomNumberServiceImpl implements RandomNumberService {

	private ThreadLocalRandom random= ThreadLocalRandom.current();

	@Override
	public int generate(int min, int max) {
		return random.nextInt(max-min+1)+min;
	}

}
