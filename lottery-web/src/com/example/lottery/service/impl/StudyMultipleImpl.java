package com.example.lottery.service.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.lottery.service.RandomNumberService;

@Named
@ApplicationScoped
public class StudyMultipleImpl {
	@Inject
	@Cheap
	private Instance<RandomNumberService> instances;
	
	@PostConstruct
	public void init() {
		System.err.println("init...");
		instances.forEach( srv -> System.err.println(srv.getClass()));
	}
	
	public void fun() {
		System.err.println("Have fun...");
	}
}
