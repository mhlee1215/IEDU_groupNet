package com.iedu.domain;

public class Group {
	int id;
	String name = "";
	String description = "";
	String status = ""; 
	String registration_date = "";
	String url = "";
	String url2 = "";
	String ownerName = "";
	int ownerId = 0;
	int viewerId = 0;
	String isJoin = "N";
	String isFavorite = "N";
	String keyword = "";
	
	String goal = "";
	String access = "";
	
	public static final String ACCESS_PUBLIC="public";
	public static final String ACCESS_PRIVATE="private";
	
	
	public String getGoal() {
		return goal;
	}



	public void setGoal(String goal) {
		this.goal = goal;
	}



	public String getAccess() {
		return access;
	}



	public void setAccess(String access) {
		this.access = access;
	}



	public String getKeyword() {
		return keyword;
	}



	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	int qSize = 10;
	//{"${member.name()}":"${member.value}", "${otherMembers}"}
	
	
	 
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"description\":\"" + description
				+ "\", \"status\":\"" + status + "\", \"registration_date\":\"" + registration_date + "\", \"url\":\""
				+ url + "\", \"url2\":\"" + url2 + "\", \"ownerName\":\"" + ownerName + "\", \"ownerId\":\"" + ownerId
				+ "\", \"viewerId\":\"" + viewerId + "\", \"isJoin\":\"" + isJoin + "\", \"isFavorite\":\"" + isFavorite
				+ "\", \"keyword\":\"" + keyword + "\", \"goal\":\"" + goal + "\", \"access\":\"" + access
				+ "\", \"qSize\":\"" + qSize + "\"}";
	}
	
	
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public int getViewerId() {
		return viewerId;
	}

	public void setViewerId(int viewerId) {
		this.viewerId = viewerId;
	}

	public String getIsJoin() {
		return isJoin;
	}

	public void setIsJoin(String isJoin) {
		this.isJoin = isJoin;
	}

	public String getIsFavorite() {
		return isFavorite;
	}

	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}

	public int getOwnerId() {
		return ownerId;
	}



	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
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

	public int getqSize() {
		return qSize;
	}

	public void setqSize(int qSize) {
		this.qSize = qSize;
	}
	
	
}
