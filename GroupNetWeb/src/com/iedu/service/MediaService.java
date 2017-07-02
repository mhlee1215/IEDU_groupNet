package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.MediaDao;
import com.iedu.dao.ProductsDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Media;
import com.iedu.domain.Products;
import com.iedu.domain.User;



@Service
public class MediaService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MediaDao		mediaDao;
	
	boolean isEncrypt = true;

	public List<Media> mediaList() {
		return mediaDao.mediaList();
	}	
	
	public Media getMedia(int id){
		return mediaDao.getMedia(id);
	}
	
	public void addMedia(Media media){
		mediaDao.addMedia(media);
	}
	
	public void updateMedia(Media media){
		mediaDao.updateMedia(media);
	}
	
	public void deleteMedia(Media media){
		mediaDao.deleteMedia(media);
	}
}
