package com.iedu.domain;
import java.util.List;

public class GroupBin {
	List<Group> groups;

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override 
	public String toString() {
		return "{\"Groups\":\"" + groups + "\"}";
	}
}