package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Media;
import com.iedu.domain.Products;
import com.iedu.domain.User;

@Repository
public class MediaDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Media> mediaList() {	
		List<Media> array = getSqlMapClientTemplate().queryForList("MediaSql.readMediaList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public Media getMedia(int id) {	
		Media pMedia = new Media();
		
		pMedia.setId(id);
		
		Media media = (Media)getSqlMapClientTemplate().queryForObject("MediaSql.readMedia", pMedia);
		return media;
	}
	
	public void addMedia(Media media){
		getSqlMapClientTemplate().insert("MediaSql.addMedia", media);
	}
	
	public void updateMedia(Media media){
		getSqlMapClientTemplate().update("MediaSql.updateMedia", media);
	}
	public void deleteMedia(Media media){
		getSqlMapClientTemplate().delete("MediaSql.deleteMedia", media);
	}
}
	

