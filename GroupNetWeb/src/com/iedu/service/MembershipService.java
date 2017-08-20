package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.MembershipDao;
import com.iedu.dao.ProductsDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Membership;
import com.iedu.domain.Products;
import com.iedu.domain.User;



@Service
public class MembershipService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MembershipDao		membershipDao;
	
	boolean isEncrypt = true;

	public List<Membership> readMembership() {
		return membershipDao.readMembership();
	}
	
	public List<Membership> readMyMembership(Membership m) {
		return membershipDao.readMyMembership(m);
	}
	
	public List<Membership> readGroupMembership(Membership m) {
		return membershipDao.readGroupMembership(m);
	}

	
	public void addMembership (Membership member){
		membershipDao.addMembership(member);
	}
	
	public void updateMembership (Membership member){
		membershipDao.updateMembership(member);
	}
	
	public void deleteMembership (Membership member){
		membershipDao.deleteMembership(member);
	}
}

