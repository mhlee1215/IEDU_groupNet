package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.Media;

@Repository
public class MediaDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 }
	
	
	@SuppressWarnings("unchecked")
	public List<Media> mediaList() {	
		List<Media> array = getSqlSession().selectList("MediaSql.readMediaList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public Media getMedia(int id) {	
		Media pMedia = new Media();
		
		pMedia.setId(id);
		
		Media media = (Media)getSqlSession().selectOne("MediaSql.readMedia", pMedia);
		return media;
	}
	
	public void addMedia(Media media){
		getSqlSession().insert("MediaSql.addMedia", media);
	}
	
	public void updateMedia(Media media){
		getSqlSession().update("MediaSql.updateMedia", media);
	}
	public void deleteMedia(Media media){
		getSqlSession().delete("MediaSql.deleteMedia", media);
	}
}
	

