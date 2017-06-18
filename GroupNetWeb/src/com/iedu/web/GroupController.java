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

import com.iedu.domain.Group;
import com.iedu.domain.Products;
import com.iedu.domain.User;
import com.iedu.service.GroupService;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;


@Controller
public class GroupController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final GroupService groupService = null;
	
	
	@RequestMapping("/readGroups.do")
    public ModelAndView readGroups(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("group_list");
		
		List<Group> groupList = groupService.readGroupList();
		model.addObject("groupList", groupList);
		
		return model;
    }
	
}
