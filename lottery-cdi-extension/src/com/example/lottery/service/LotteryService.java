package com.example.lottery.service;

import java.util.Collection;

public interface LotteryService {
	Collection<Integer> draw();
	Collection<Integer> draw(int min,int max,int size,boolean sorted);
}
