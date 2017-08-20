package com.iedu.domain;

public class Group {
	int id;
	String name = "";
	String description = "";
	String status = ""; 
	String registration_date = "";
	String url = "";
	String url2 = ""; 
	//{"${member.name()}":"${member.value}", "${otherMembers}"}
	
	
	
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"description\":\"" + description
				+ "\", \"status\":\"" + status + "\", \"registration_date\":\"" + registration_date + "\", \"url\":\""
				+ url + "\", \"url2\":\"" + url2 + "\"}";
	}
	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(String registration_date) {
		this.registration_date = registration_date;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl2() {
		return url2;
	}

	public void setUrl2(String url2) {
		this.url2 = url2;
	}

	public static void main(String[] argv){
		Group g = new Group();
		System.out.println(g);
	}
	
	
}
