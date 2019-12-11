package com.example.lottery.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * @author Binnur Kurt <binnur.kurt@gmail.com>
 *
 */
@WebListener
public class SessionAuditListener implements HttpSessionListener , HttpSessionAttributeListener {

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.err.println(String.format("New attribute (%s) is added to the session %s ",event.getName(),event.getSession().getId()));
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.err.println(String.format("Attribute (%s) is replaced at the session %s ",event.getName(),event.getSession().getId()));
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.err.println(String.format("New session (%s) is created",se.getSession().getId()));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.err.println(String.format("Session (%s) is destroyed",se.getSession().getId()));
	}

}
