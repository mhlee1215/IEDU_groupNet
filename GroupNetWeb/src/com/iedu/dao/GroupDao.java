package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Group;
import com.iedu.domain.Products;
import com.iedu.domain.User;

@Repository
public class GroupDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<Group> readGroup() {	
		List<Group> array = getSqlMapClientTemplate().queryForList("GroupSql.readGroupList");
		return array;
	}
	
	public void addGroup(Group group) {
		System.out.println("<<<<"+group);
		getSqlMapClientTemplate().insert("GroupSql.addGroup", group);
	}
	
	public void updateGroup(Group group){
		getSqlMapClientTemplate().update("GroupSql.updateGroup", group);
	}
	
	public void deleteGroup(Group group){
		getSqlMapClientTemplate().delete("GroupSql.deleteGroup", group);
	}
}
