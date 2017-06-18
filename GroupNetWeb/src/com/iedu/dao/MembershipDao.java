package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Membership;
import com.iedu.domain.Products;
import com.iedu.domain.User;

@Repository
public class MembershipDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Membership> readMembership() {	
		List<Membership> array = getSqlMapClientTemplate().queryForList("MembershipSql.readMembershipList");
		return array;
	}
	public void addMembership(Membership member){
		getSqlMapClientTemplate().insert("MembershipSql.addMembership", member);
}

public void updateMembership(Membership member){
	getSqlMapClientTemplate().insert("MembershipSql.updateMembership", member);
}
public void deleteMembership(Membership member){
	getSqlMapClientTemplate().insert("MembershipSql.deleteMembership", member);
}
}
