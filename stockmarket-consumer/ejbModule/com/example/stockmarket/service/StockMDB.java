package com.example.stockmarket.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: StockMDB
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/jms/stockQueue") })
public class StockMDB implements MessageListener {

	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				System.err.println(textMessage.getText());
			} catch (JMSException e) {
				System.err.println(e.getMessage());
			}
		}

	}

}
