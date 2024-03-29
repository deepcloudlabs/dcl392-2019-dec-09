package com.example.lottery.service.impl;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import com.example.lottery.service.RandomNumberService;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@Named
@Singleton
@Efficient
//@Alternative
public class FastRandomNumberService implements RandomNumberService {

	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("@Efficient");
		return random.nextInt(max - min + 1) + min;
	}

}
