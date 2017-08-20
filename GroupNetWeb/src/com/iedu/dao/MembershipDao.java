package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.Membership;

@Repository
public class MembershipDao extends SqlSessionDaoSupport{

	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
		super.setSqlSessionFactory(sqlSessionFactory);
	}


	@SuppressWarnings("unchecked")
	public List<Membership> readMembership() {	
		List<Membership> array = getSqlSession().selectList("MembershipSql.readMembershipList");
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Membership> readMyMembership(Membership m) {	
		List<Membership> array = getSqlSession().selectList("MembershipSql.readMyMembershipList", m);
		return array;
	}

	@SuppressWarnings("unchecked")
	public List<Membership> readGroupMembership(Membership m) {	
		List<Membership> array = getSqlSession().selectList("MembershipSql.readGroupMembershipList", m);
		return array;
	}

	public void addMembership(Membership member){
		getSqlSession().insert("MembershipSql.addMembership", member);
	}

	public void updateMembership(Membership member){
		getSqlSession().insert("MembershipSql.updateMembership", member);
	}
	public void deleteMembership(Membership member){
		getSqlSession().insert("MembershipSql.deleteMembership", member);
	}
}
