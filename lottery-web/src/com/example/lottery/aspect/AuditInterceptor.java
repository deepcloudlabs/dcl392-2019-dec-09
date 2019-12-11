package com.example.lottery.aspect;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@SuppressWarnings("serial")
@Interceptor
@Audit
public class AuditInterceptor implements Serializable {
	@AroundInvoke
	public Object audit(InvocationContext ic) throws Exception {
		String methodName = ic.getMethod().getName();
		Date now = new Date();
		System.err.println(String.format("%s is called at %s", methodName, now));
		System.err.println(String.format("Parameters are %s", Arrays.toString(ic.getParameters())));
		Object result = ic.proceed();
		System.err.println(String.format("%s returns %s", methodName, result));
		return result;
	}
}
