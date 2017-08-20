package com.iedu.dao;

import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import com.iedu.domain.Message;
import com.iedu.domain.Products;
import com.iedu.domain.User;

@Repository
public class MessageDao extends SqlSessionDaoSupport{
	
	@Resource
	  public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
	    super.setSqlSessionFactory(sqlSessionFactory);
	 }
	
	
	@SuppressWarnings("unchecked")
	public List<Message> messageList() {	
		List<Message> array = getSqlSession().selectList("MessageSql.readMessageList");
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public Message getMessage(int sendID, int recieveID) {	
		Message pMessage = new Message();
		
		pMessage.setSendID(sendID);
		pMessage.setRecieveID(recieveID);
		
		Message message = (Message) getSqlSession().selectOne("MessageSql.readMessage", pMessage);
		return message;
	}
	
	public void addMessage(Message message){
		getSqlSession().insert("MessageSql.addMessage", message);
	}
	
	public void updateMessage(Message message){
		getSqlSession().update("MessageSql.updateMessage", message);
	}
	
	public void deleteMessage(Message message){
		getSqlSession().delete("MessageSql.deleteMessage", message);
	}
}
