package com.example.stockmarket.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.json.Json;
import javax.json.bind.JsonbBuilder;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.example.stockmarket.events.StockPriceChangedEvent;

@Singleton
@ServerEndpoint("/stockmarket")
public class StockMarketWebsocketService {
	private Map<String, Session> sessions = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(Session session) {
		sessions.put(session.getId(), session);
	}

	@OnClose
	public void onClose(Session session) {
		sessions.remove(session.getId());
	}

	public void stockPriceChanged(@Observes StockPriceChangedEvent event) {
		String json = createJsonp(event);
		sessions.forEach((id, session) -> sendEvent(session, json));
	}

	private void sendEvent(Session session, String json) {
		try {
			session.getBasicRemote().sendText(json);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	private String createJsonp(StockPriceChangedEvent event) {
		return Json.createObjectBuilder().add("symbol", event.getSymbol()).add("oldPrice", event.getOldPrice())
				.add("newPrice", event.getNewPrice()).build().toString();
	}

	@SuppressWarnings("unused")
	private String createJsonb(StockPriceChangedEvent event) {
		return JsonbBuilder.newBuilder().build().toJson(event);
	}
}
