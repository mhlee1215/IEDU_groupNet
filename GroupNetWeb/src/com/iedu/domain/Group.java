package com.iedu.domain;

public class Group {
	int id;
	String name;
	String description;
	//..
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
