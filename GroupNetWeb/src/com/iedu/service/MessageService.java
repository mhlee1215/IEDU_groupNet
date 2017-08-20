package com.iedu.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iedu.dao.MessageDao;
import com.iedu.dao.ProductsDao;
import com.iedu.dao.UserDao;
import com.iedu.domain.Message;
import com.iedu.domain.Products;
import com.iedu.domain.User;



@Service
public class MessageService{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MessageDao	messageDao;
	
	boolean isEncrypt = true;

	public List<Message> messageList() {
		return messageDao.messageList();
	}	
	
	public Message getMessage(int sendID, int recieveID){
		return messageDao.getMessage(sendID, recieveID);
	}
	
	public void addMessage(Message message){
		messageDao.addMessage(message);
	}
	
	public void updateMessage(Message message){
		messageDao.updateMessage(message);
	}
	
	public void deleteMessage(Message message){
		messageDao.deleteMessage(message);
	}
}
