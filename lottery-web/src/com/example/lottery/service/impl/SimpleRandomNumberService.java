package com.example.lottery.service.impl;

import java.security.SecureRandom;

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
//@Default
@Cheap
public class SimpleRandomNumberService implements RandomNumberService {
	private SecureRandom random = new SecureRandom();

	@Override
	public int generate(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

}
