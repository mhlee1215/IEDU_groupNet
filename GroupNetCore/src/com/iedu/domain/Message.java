package com.iedu.domain;

public class Message {
	int id;
	int sendID;
	int recieveID;
	String text = "";
	String time = "";
	long createdAt = 0;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSendID() {
		return sendID;
	}
	public void setSendID(int sendID) {
		this.sendID = sendID;
	}
	public int getRecieveID() {
		return recieveID;
	}
	public void setRecieveID(int recieveID) {
		this.recieveID = recieveID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"sendID\":\"" + sendID + "\", \"recieveID\":\"" + recieveID + "\", \"text\":\""
				+ text + "\", \"time\":\"" + time + "\", \"createdAt\":\"" + createdAt + "\"}";
	}

}
