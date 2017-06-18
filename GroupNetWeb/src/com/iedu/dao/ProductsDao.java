package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.iedu.domain.Products;

@Repository
public class ProductsDao extends SqlMapClientDaoSupport{
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	
	@SuppressWarnings("unchecked")
	public List<Products> findAll() {	
		List<Products> array = getSqlMapClientTemplate().queryForList("ProductsSql.readProductsList");
		return array;
	}


	public Products readUser(Products products) {
		Products result = (Products)getSqlMapClientTemplate().queryForObject("ProductsSql.readProducts", products);
		return result;
	}


	public void createUser(Products products) {
		getSqlMapClientTemplate().insert("ProductsSql.createProducts", products);
	}


	public void deleteUser(Products products) {
		getSqlMapClientTemplate().delete("ProductsSql.deleteProducts", products);
		
	}


	public void updateUser(Products products) {
		getSqlMapClientTemplate().update("ProductsSql.updateProducts", products);
	}

}
