package com.iedu.domain;
import java.util.List;

public class MembershipBin {
	List<Membership> membership;

	public List<Membership> getMemberships() {
		return membership;
	}

	public void setMemberships(List<Membership> membership) {
		this.membership = membership;
	}

	@Override 
	public String toString() {
		return "{\"Membership\":\"" + membership + "\"}";
	}
} 