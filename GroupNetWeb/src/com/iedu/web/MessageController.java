package com.iedu.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iedu.domain.Message;
import com.iedu.domain.Products;
import com.iedu.domain.Signal;
import com.iedu.domain.User;
import com.iedu.service.MessageService;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;
import com.iedu.util.MyJsonUtil;


@Controller
public class MessageController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final MessageService messageService = null;
	
	@RequestMapping("/showMessage.do")
    public ResponseEntity<String> showMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		ModelAndView model = new ModelAndView("show_message");
		List<Message> messageList = messageService.messageList();
		int pSendID = 			ServletRequestUtils.getIntParameter(request, "sendID"		, 0);
		int pRecieveID = 		ServletRequestUtils.getIntParameter(request, "recieveID"	, 0);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		//return new ResponseEntity<String>();
		return new ResponseEntity<String>(MyJsonUtil.toString(messageList, "message"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/send.do") //  http://localhost:8080/WebTemplate/addUser.do
    public @ResponseBody String addMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = 			ServletRequestUtils.getIntParameter(request, "id"		, 0);
		int pSendID = 			ServletRequestUtils.getIntParameter(request, "sendID"		, 0);
		int pRecieveID = 		ServletRequestUtils.getIntParameter(request, "recieveID"	, 0);
		String pText = 			ServletRequestUtils.getStringParameter(request, "text"		, "");
		String pTime = 		ServletRequestUtils.getStringParameter(request, "time"		, "");
		
		Message pMessage = new Message();
		pMessage.setId(pId);
		pMessage.setSendID(pSendID);
		pMessage.setRecieveID(pRecieveID);
		pMessage.setText(pText);
		pMessage.setTime(pTime);
		
		
		messageService.addMessage(pMessage);
		
		return "added!";
    }

}
