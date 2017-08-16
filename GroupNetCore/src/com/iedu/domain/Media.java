package com.iedu.domain;

public class Media {
	int id;
	String path;
	String name;
	String date;
	String size;
	String type; 
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"path\":\"" + path + "\", \"name\":\"" + name + "\", \"date\":\"" + date
				+ "\", \"size\":\"" + size + "\", \"type\":\"" + type + "\"}";
	}
}
