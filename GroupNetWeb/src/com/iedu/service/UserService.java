package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.ProductsDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Products;
import com.iedu.domain.User;



@Service
public class UserService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDao		userDao;
	
	boolean isEncrypt = true;

	public List<User> userList() {
		return userDao.userList();
	}	
	
	public User getUser(String name, String password){
		return userDao.getUser(name, password);
	}
	
	public void addUser(User user){
		userDao.addUser(user);
	}
	
	public void updateUser(User user){
		userDao.updateUser(user);
	}
	
	public void deleteUser(User user){
		userDao.deleteUser(user);
	}
	
	public int signUpUser(User user) {
		//
		User curry = getUser(user.getName(), "");
		
		if(curry == null){
			userDao.addUser(user);
			return 1;
			//Return success code
		}else{
			//Return unsuccess code
			return 0;
		}
	}
	
	public int logInUser(User user) {
		//
		User goat = getUser(user.getName(), user.getPassword());
		
		if(goat != null){
			return 1;
			//Return success code
		}else{
			//Return unsuccess code
			return 0;
		}
	}
}
