package com.iedu.web;

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
import com.iedu.domain.Tag;
import com.iedu.domain.User;
import com.iedu.service.ProductsService;
import com.iedu.service.TagService;
import com.iedu.service.UserService;


@Controller
public class MainController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final ProductsService productsService = null;
	
	@Autowired
	private final TagService tagService = null;

	@Autowired
	private final UserService userService = null;

	@RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("index");	
		
		List<Products> results = productsService.findAll();
		System.out.println(results);
		
		model.addObject("products", results);
		return model;
    }
	@RequestMapping("/showUsers.do")
    public ModelAndView showUsers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String PId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		ModelAndView model = new ModelAndView("show_users");
		List<User> userList = userService.userList();
		model.addObject("userList", userList);
		return model;
    }
	
	@RequestMapping("/loginTest.do")
    public ModelAndView showProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		ModelAndView model = new ModelAndView("login_test");				
		model.addObject("pId", pId);
		model.addObject("pPassword", pPassword);
		return model;
    }
	
	// ... getUser.do?id=...&email=...& ...
	@RequestMapping("/getUser.do")
    public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pEmail = ServletRequestUtils.getStringParameter(request, "email", "");
		int pAge = ServletRequestUtils.getIntParameter(request, "age", 0);
		String pAddress = ServletRequestUtils.getStringParameter(request, "address", "");
		
		User user = new User();
		user.setEmail(pEmail);
		user.setAge(pAge);
		user.setAddress(pAddress);
		List<User> result = null;//userService.getUser(user);
		
		ModelAndView model = new ModelAndView("show_user");				
		model.addObject("user", result);
		return model;
    }
	
	@RequestMapping("/addUser.do")
    public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		String pPhoneNumber = ServletRequestUtils.getStringParameter(request, "phonenumber", "");
		String pEmail = ServletRequestUtils.getStringParameter(request, "email", "");
		int pAge = ServletRequestUtils.getIntParameter(request, "age", 0);
		String pAddress = ServletRequestUtils.getStringParameter(request, "address", "");
		
		User user = new User();
		user.setName(pName);
		user.setPassword(pPassword);
		user.setEmail(pEmail);
		user.setAge(pAge);
		user.setAddress(pAddress);
		user.setPhoneNumber(pPhoneNumber);
		userService.addUser(user);
		
		ModelAndView model = new ModelAndView("show_user");				
		model.addObject("user", user);
		return model;
    }
	
	@RequestMapping("/updateUser.do")
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		String pPhoneNumber = ServletRequestUtils.getStringParameter(request, "phonenumber", "");
		String pEmail = ServletRequestUtils.getStringParameter(request, "email", "");
		int pAge = ServletRequestUtils.getIntParameter(request, "age", 0);
		String pAddress = ServletRequestUtils.getStringParameter(request, "address", "");
		
		User user = new User();
		user.setName(pName);
		user.setPassword(pPassword);
		user.setEmail(pEmail);
		user.setAge(pAge);
		user.setAddress(pAddress);
		user.setPhoneNumber(pPhoneNumber);
		userService.updateUser(user);
		
		ModelAndView model = new ModelAndView("show_user");				
		model.addObject("user", user);
		return model;
}
	@RequestMapping("/delteUser.do")
    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pId = ServletRequestUtils.getStringParameter(request, "id", "");
		String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		String pPassword = ServletRequestUtils.getStringParameter(request, "password", "");
		String pPhoneNumber = ServletRequestUtils.getStringParameter(request, "phonenumber", "");
		String pEmail = ServletRequestUtils.getStringParameter(request, "email", "");
		int pAge = ServletRequestUtils.getIntParameter(request, "age", 0);
		String pAddress = ServletRequestUtils.getStringParameter(request, "address", "");
		
		User user = new User();
		user.setName(pName);
		user.setPassword(pPassword);
		user.setEmail(pEmail);
		user.setAge(pAge);
		user.setAddress(pAddress);
		user.setPhoneNumber(pPhoneNumber);
		userService.deleteUser(user);
		
		ModelAndView model = new ModelAndView("show_user");				
		model.addObject("user", user);
		return model;
	}
	
	// ... getTag.do?id=...&email=...& ...
		@RequestMapping("/getTag.do")
	    public @ResponseBody String getTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String pId = ServletRequestUtils.getStringParameter(request, "id", "");
			String pName = ServletRequestUtils.getStringParameter(request, "name", "");
		
			Tag tag = new Tag();
			tag.setName(pName);
			List<Tag> result = tagService.getTag(tag);
			
			//ModelAndView model = new ModelAndView("show_tag");				
			//model.addObject("tag", result);
			return "get tag result";
	    }
		@RequestMapping("/addTag.do")
	    public ModelAndView addTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String pId = ServletRequestUtils.getStringParameter(request, "id", "");
			String pName = ServletRequestUtils.getStringParameter(request, "name", "");
			
			Tag tag = new Tag();
			tag.setName(pName);
			tagService.addTag(tag);
			
			ModelAndView model = new ModelAndView("show_tag");				
			model.addObject("tag", tag);
			return model;
	    }
		@RequestMapping("/deleteTag.do")
	    public ModelAndView deleteTag(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String pId = ServletRequestUtils.getStringParameter(request, "id", "");
			String pName = ServletRequestUtils.getStringParameter(request, "name", "");
			
			Tag tag = new Tag();
			tag.setName(pName);
			tagService.deleteTag(tag);
			
			ModelAndView model = new ModelAndView("show_tag");				
			model.addObject("tag", tag);
			return model;
		}
	
}
