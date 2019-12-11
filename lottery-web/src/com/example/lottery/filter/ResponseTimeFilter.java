package com.example.lottery.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebFilter(urlPatterns = "/*")
public class ResponseTimeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		long start = System.currentTimeMillis();
		chain.doFilter(request, response);
		long stop = System.currentTimeMillis();
		System.err.println(String.format("%4s %24s %5d ms.", req.getMethod(), req.getServletPath(), (stop - start)));
	}

}
