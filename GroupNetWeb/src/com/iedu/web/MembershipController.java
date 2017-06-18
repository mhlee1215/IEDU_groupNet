package com.iedu.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.iedu.domain.Membership;
import com.iedu.domain.Products;
import com.iedu.domain.User;
import com.iedu.service.MembershipService;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;


@Controller
public class MembershipController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final MembershipService membershipService = null;
	
//	@Autowired
//	private final UserService userService = null;
//	
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
	
	@RequestMapping("/readMemberships.do")
    public ModelAndView readMemberships(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("membership_list");	
		
		List<Membership> membershipList = membershipService.readMembership();
		model.addObject("membershipList", membershipList);
		
		return model;
    }


	@RequestMapping("/addMembership.do")
    public ModelAndView loginTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//localhost:8080/WebTemplate/addMembership.do?id=10&name=abc&password...
		
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		int pUserID = ServletRequestUtils.getIntParameter(request, "userID", 0);
		int pGroupID = ServletRequestUtils.getIntParameter(request, "groupID", 0);
		String pEnrollDate = ServletRequestUtils.getStringParameter(request, "enrollDate", "");


		Membership pMembership = new Membership();
		pMembership.setId(pId);
		pMembership.setUserID(pUserID);
		pMembership.setGroupID(pGroupID);
		pMembership.setEnrollDate(pEnrollDate);
		
		
		membershipService.addMembership(pMembership);
		membershipService.updateMembership(pMembership);
		
		ModelAndView model = new ModelAndView("add_membership_test");
		 model.addObject("id", pId);
		 model.addObject("userID", pUserID);
		 model.addObject("groupID", pGroupID);
		 model.addObject("enrollDate", pEnrollDate);

	
		return model;
    }
	
	@RequestMapping("/updateMembership.do")
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//localhost:8080/WebTemplate/updateMembership.do?id=10&name=abc&password...
		
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		int pUserID = ServletRequestUtils.getIntParameter(request, "userID", 0);
		int pGroupID = ServletRequestUtils.getIntParameter(request, "groupID", 0);
		String pEnrollDate = ServletRequestUtils.getStringParameter(request, "enrollDate", "");

		Membership pMembership = new Membership();
		pMembership.setId(pId);
		pMembership.setUserID(pUserID);
		pMembership.setGroupID(pGroupID);
		pMembership.setEnrollDate(pEnrollDate);
		
		
		membershipService.updateMembership(pMembership);
		
		ModelAndView model = new ModelAndView("add_membership_test");
		 model.addObject("id", pId);
		 model.addObject("userID", pUserID);
		 model.addObject("groupID", pGroupID);
		 model.addObject("enrollDate", pEnrollDate);
	
		return model;
    }
	@RequestMapping("/deleteMembership.do")
    public ModelAndView deleteMembership(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//localhost:8080/WebTemplate/deleteMembership.do?id=10&name=abc&password...
		
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		int pUserID = ServletRequestUtils.getIntParameter(request, "userID", 0);
		int pGroupID = ServletRequestUtils.getIntParameter(request, "groupID", 0);
		String pEnrollDate = ServletRequestUtils.getStringParameter(request, "enrollDate", "");

		Membership pMembership = new Membership();
		pMembership.setId(pId);
		pMembership.setUserID(pUserID);
		pMembership.setGroupID(pGroupID);
		pMembership.setEnrollDate(pEnrollDate);
		
		membershipService.deleteMembership(pMembership);
		
		ModelAndView model = new ModelAndView("add_membership_test");
		 model.addObject("id", pId);
		 model.addObject("userID", pUserID);
		 model.addObject("groupID", pGroupID);
		 model.addObject("enrollDate", pEnrollDate);
		
		return model;
    }
	
}
