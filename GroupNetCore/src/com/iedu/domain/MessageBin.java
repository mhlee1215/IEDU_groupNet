package com.iedu.domain;
import java.util.List;

public class MessageBin {
	List<Message> message;

	public List<Message> getMessages() {
		return message;
	}

	public void setMessages(List<Message> message) {
		this.message = message;
	}

	@Override 
	public String toString() {
		return "{\"message\":\"" + message + "\"}";
	}
} 