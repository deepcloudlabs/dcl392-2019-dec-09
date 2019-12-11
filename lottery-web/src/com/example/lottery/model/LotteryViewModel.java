package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.example.lottery.aspect.Audit;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SuppressWarnings("serial")
@Named("lottery")
@SessionScoped
@Audit
public class LotteryViewModel implements Serializable {
	private List<List<Integer>> numbers = new ArrayList<>();

	public LotteryViewModel() {
	}

	public List<List<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "LotteryViewModel [numbers=" + numbers + "]";
	}

}
