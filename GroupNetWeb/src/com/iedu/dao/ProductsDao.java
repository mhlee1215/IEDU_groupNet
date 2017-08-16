package com.iedu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iedu.domain.Products;

@Repository
public class ProductsDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Products> findAll() {	
		List<Products> array = getSqlSession().selectList("ProductsSql.readProductsList");
		return array;
	}


	public Products readUser(Products products) {
		Products result = (Products)getSqlSession().selectOne("ProductsSql.readProducts", products);
		return result;
	}


	public void createUser(Products products) {
		getSqlSession().insert("ProductsSql.createProducts", products);
	}


	public void deleteUser(Products products) {
		getSqlSession().delete("ProductsSql.deleteProducts", products);
		
	}


	public void updateUser(Products products) {
		getSqlSession().update("ProductsSql.updateProducts", products);
	}

}
