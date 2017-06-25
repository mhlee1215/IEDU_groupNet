package com.iedu.domain;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Products;
import com.iedu.domain.Tag;
import com.iedu.domain.User;

@Repository
public class TagDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Tag> TagList() {	
		List<Tag> array = getSqlMapClientTemplate().queryForList("TagSql.readTagList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> getTag(Tag tag) {	
		List<Tag> result = getSqlMapClientTemplate().queryForList("TagSql.readTag", tag);
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void addTag(Tag tag) {	
		getSqlMapClientTemplate().insert("TagSql.addTag", tag);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteTag(Tag tag) {	
		getSqlMapClientTemplate().delete("TagSql.deleteTag", tag);
	}
}
