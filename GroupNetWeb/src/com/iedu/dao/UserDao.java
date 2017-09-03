package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.User;

@Repository
public class UserDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 }
	
	
	@SuppressWarnings("unchecked")
	public List<User> userList() {	
		List<User> array = getSqlSession().selectList("UserSql.readUserList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public User getUser(String name, String password) {	
		User pUser = new User();
		
		pUser.setName(name);
		pUser.setPassword(password);
		
		User user = (User) getSqlSession().selectOne("UserSql.readUser", pUser);
		return user;
	}
	
	public User getUser(User pUser) {	
		User user = (User) getSqlSession().selectOne("UserSql.readUser", pUser);
		return user;
	}
	
	public void addUser(User user){
		getSqlSession().insert("UserSql.addUser", user);
	}
	
	public void updateUser(User user){
		getSqlSession().update("UserSql.updateUser", user);
	}
	
	public void deleteUser(User user){
		getSqlSession().delete("UserSql.deleteUser", user);
	}
}
