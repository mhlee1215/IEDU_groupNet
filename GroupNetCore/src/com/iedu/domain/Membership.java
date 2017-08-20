package com.iedu.domain;

public class Membership {
	
	
	int id;
	int userID = 0;
	int groupID  = 0;
	String enrollDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	@Override
	public String toString() {
		return "Membership [id=" + id + ", userID=" + userID + ", groupID=" + groupID + ", enrollDate=" + enrollDate
				+ "]";
	}
	
	
	
	
	
	
	
	
	
}
