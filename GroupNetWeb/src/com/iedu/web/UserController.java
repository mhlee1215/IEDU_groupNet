package com.iedu.web;

import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iedu.domain.Products;
import com.iedu.domain.Signal;
import com.iedu.domain.User;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;


@Controller
public class UserController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final UserService userService = null;
	
//	@RequestMapping("/index.do")
//    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView("index");	
//		
//		List<Products> results = productsService.findAll();
//		System.out.println(results);
//		
//		model.addObject("products", results);
//		return model;
//    }
//	
//	
	@RequestMapping("/showUsers.do")
    public ModelAndView showUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		ModelAndView model = new ModelAndView("show_users");
		List<User> userList = userService.userList();
		model.addObject("userList", userList);
		return model;
    }
	
	@RequestMapping("/login.do")
    public @ResponseBody String loginTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		
		pName = URLDecoder.decode(pName, "UTF-8");
		pPassword = URLDecoder.decode(pPassword, "UTF-8");
		
		User user = userService.getUser(pName, pPassword);
		
		if(user != null)
			return user.getId()+"";//  Integer.toString(Signal.LOGIN_SUCCESS);
		else
			return Integer.toString(Signal.LOGIN_FAIL);
    }
//	
//	
//	
//	@RequestMapping("/showProducts.do")
//    public ModelAndView showProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model = new ModelAndView("index");				
//		return model;
//    }
//	
	@RequestMapping("/addUser.do") //  http://localhost:8080/WebTemplate/addUser.do
    public @ResponseBody String adduser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = 			ServletRequestUtils.getIntParameter(request, "id"		, 0);
		String pName = 			ServletRequestUtils.getStringParameter(request, "name"		, "");
		String pPassword = 		ServletRequestUtils.getStringParameter(request, "password"	, "");
		int pAge = 			ServletRequestUtils.getIntParameter(request, "age"		, 0);
		String pEmail = 		ServletRequestUtils.getStringParameter(request, "email"		, "");
		String pAddress = 		ServletRequestUtils.getStringParameter(request, "address"	, "");
		String pPhoneNumber = 	ServletRequestUtils.getStringParameter(request, "phoneNumber", "");
		
		User pUser = new User();
		pUser.setId(pId);
		pUser.setName(pName);
		pUser.setPassword(pPassword);
		pUser.setAge(pAge);
		pUser.setEmail(pEmail);
		pUser.setAddress(pAddress);
		pUser.setPhoneNumber(pPhoneNumber);
		
		System.out.println("user to be added?"+pUser);
		userService.addUser(pUser);
		
		return "added!";
    }
	
	@RequestMapping("/signupTest.do")
    public @ResponseBody String signupTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		
		User pUser = new User();
		pUser.setName(pName);
		pUser.setPassword(pPassword);
		int errorCode = userService.signUpUser(pUser);
		
		
//		ModelAndView model = new ModelAndView("login_test");
//		model.addObject("pName", pName);
//		model.addObject("pPassword", pPassword);
//		model.addObject("user", user);
		return "successful_code_"+errorCode;
    }
}
