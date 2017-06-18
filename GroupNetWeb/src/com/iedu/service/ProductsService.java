package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.ProductsDao;
import com.iedu.domain.Products;



@Service
public class ProductsService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ProductsDao		productsDao;
	
	boolean isEncrypt = true;

	public List<Products> findAll() {
		return productsDao.findAll();
	}	
}
