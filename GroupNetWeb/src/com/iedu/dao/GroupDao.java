package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.Group;

@Repository
public class GroupDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 }
	
	@SuppressWarnings("unchecked")
	public List<Group> readGroup() {	
		List<Group> array = getSqlSession().selectList("GroupSql.readGroupList");
		return array;
	}
	
	public void addGroup(Group group) {
		System.out.println("<<<<"+group);
		getSqlSession().insert("GroupSql.addGroup", group);
	}
	
	public void updateGroup(Group group){
		getSqlSession().update("GroupSql.updateGroup", group);
	}
	
	public void deleteGroup(Group group){
		getSqlSession().delete("GroupSql.deleteGroup", group);
	}
}
