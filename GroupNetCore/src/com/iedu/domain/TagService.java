package com.iedu.domain;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.ProductsDao;
import com.iedu.dao.TagDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Products;
import com.iedu.domain.Tag;
import com.iedu.domain.User;



@Service
public class TagService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private TagDao		tagDao;
	
	boolean isEncrypt = true;

	public List<Tag> TagList() {
		return tagDao.TagList();
	}	
	
	public List<Tag> getTag(Tag tag) {	
		return tagDao.getTag(tag);
	}
	
	public void addTag(Tag tag) {	
		tagDao.addTag(tag);
	}
	
	public void deleteTag(Tag tag) {	
		tagDao.deleteTag(tag);
	}
}
