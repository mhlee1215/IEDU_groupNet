package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.Tag;

@Repository
public class TagDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Tag> TagList() {	
		List<Tag> array = getSqlSession().selectList("TagSql.readTagList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> getTag(Tag tag) {	
		List<Tag> result = getSqlSession().selectList("TagSql.readTag", tag);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void addTag(Tag tag) {	
		getSqlSession().insert("TagSql.addTag", tag);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteTag(Tag tag) {	
		getSqlSession().delete("TagSql.deleteTag", tag);
	}
}
