package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.GroupDao;
import com.iedu.dao.ProductsDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Group;
import com.iedu.domain.Products;
import com.iedu.domain.User;



@Service
public class GroupService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private GroupDao groupDao;
	
	boolean isEncrypt = true;

	public List<Group> readGroup() {
		return groupDao.readGroup();
	}	
	
	public void addGroup(Group group) {
		groupDao.addGroup(group);
	}
	
	public void updateGroup(Group group) {
		groupDao.updateGroup(group);
	}
	
	public void deleteGroup(Group group) {
		groupDao.deleteGroup(group);
	}
}
