package com.iedu.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iedu.domain.Group;
import com.iedu.domain.Products;
import com.iedu.domain.User;
import com.iedu.service.GroupService;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;
import com.iedu.util.MyJsonUtil;


@Controller
public class GroupController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final GroupService groupService = null;
		
	@RequestMapping("/readGroup.do")
    public ResponseEntity<String> readGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("group_list");
		
		List<Group> groupList = groupService.readGroup();
		model.addObject("groupList", groupList);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		//return new ResponseEntity<String>();
		return new ResponseEntity<String>(MyJsonUtil.toString(groupList, "groups"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/addGroup.do")
    public @ResponseBody String addGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pDescription = ServletRequestUtils.getStringParameter(request, "description", "");
		String pStatus = ServletRequestUtils.getStringParameter(request, "status", "");
		//String pRegistration_date = ServletRequestUtils.getStringParameter(request, "registration_date", "");
		String pUrl = ServletRequestUtils.getStringParameter(request, "url", "");

		try {
			pName = URLDecoder.decode(pName, "UTF-8");
			pDescription = URLDecoder.decode(pDescription, "UTF-8");
			pUrl = URLDecoder.decode(pUrl, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Group pGroup = new Group();
		//pGroup.setId(pId);
		pGroup.setName(pName);
		pGroup.setDescription(pDescription);
		pGroup.setStatus(pStatus);
		//pGroup.setRegistration_date(pRegistration_date);
		pGroup.setUrl2(pUrl);
		
		System.out.println(">>>"+pGroup);

		groupService.addGroup(pGroup);
		
//		ModelAndView model = new ModelAndView("add_group");	
//		
//		model.addObject("id", pId);
//		model.addObject("name", pName);
//		model.addObject("description", pDescription);
//		model.addObject("status", pStatus);
//		model.addObject("registration_date", pRegistration_date);
//		model.addObject("url", pUrl);

		return "added!";
    }
	
	@RequestMapping("/updateGroup.do")
    public ModelAndView updateGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pDescription = ServletRequestUtils.getStringParameter(request, "description", "");
		String pStatus = ServletRequestUtils.getStringParameter(request, "status", "");
		String pRegistration_date = ServletRequestUtils.getStringParameter(request, "registration_date", "");
		String pUrl = ServletRequestUtils.getStringParameter(request, "url", "");

		Group pGroup = new Group();
		pGroup.setId(pId);
		pGroup.setName(pName);
		pGroup.setDescription(pDescription);
		pGroup.setStatus(pStatus);
		pGroup.setRegistration_date(pRegistration_date);
		pGroup.setUrl(pUrl);

		groupService.updateGroup(pGroup);
		
		ModelAndView model = new ModelAndView("update_group");	
		
		return model;
    }
	
	@RequestMapping("/deleteGroup.do")
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
//		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
//		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
//		int pAge = ServletRequestUtils.getIntParameter(request, "age", 0);
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
//		String pAddress = ServletRequestUtils.getStringParameter(request, "address", "");
//		String pPhonenumber = ServletRequestUtils.getStringParameter(request, "phonenumber", "");
//
		Group pGroup = new Group();
		pGroup.setId(pId);
//		pUser.setName(pName);
//		pUser.setPassword(pPassword);
//		pUser.setAge(pAge);
//		pUser.setEmail(pEmail);
//		pUser.setAddress(pAddress);
//		pUser.setPhonenumber(pPhonenumber);

		groupService.deleteGroup(pGroup);
		
		ModelAndView model = new ModelAndView("delete_group");	
		
		return model;
    }
	
}
