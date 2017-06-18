package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Products;
import com.iedu.domain.User;

@Repository
public class UserDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<User> userList() {	
		List<User> array = getSqlMapClientTemplate().queryForList("UserSql.readUserList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public User getUser(String name, String password) {	
		User pUser = new User();
		
		pUser.setName(name);
		pUser.setPassword(password);
		
		User user = (User) getSqlMapClientTemplate().queryForObject("UserSql.readUser", pUser);
		return user;
	}
	
	public void addUser(User user){
		getSqlMapClientTemplate().insert("UserSql.addUser", user);
	}
	
	public void updateUser(User user){
		getSqlMapClientTemplate().update("UserSql.updateUser", user);
	}
	
	public void deleteUser(User user){
		getSqlMapClientTemplate().delete("UserSql.deleteUser", user);
	}
}
