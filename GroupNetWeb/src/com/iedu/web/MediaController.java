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

import com.iedu.domain.Media;
import com.iedu.domain.Products;
import com.iedu.domain.User;
import com.iedu.service.MediaService;
import com.iedu.service.ProductsService;
import com.iedu.service.UserService;


@Controller
public class MediaController {

	private Logger logger = Logger.getLogger(getClass());
	
	
	@Autowired
	private final MediaService mediaService = null;

	
	
	
	@RequestMapping("/showMedias.do")
    public ModelAndView showMedias(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("show_medias");
		
		List<Media> mediaList = mediaService.mediaList();
		System.out.print(mediaList);
		model.addObject("medias", mediaList);
		
		return model;
    }
	
	
	@RequestMapping("/addMedia.do")
    public ModelAndView addMedia(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		String pPath = ServletRequestUtils.getStringParameter(request, "path", "");
		String pDate = ServletRequestUtils.getStringParameter(request, "date", "");
		String pSize = ServletRequestUtils.getStringParameter(request, "size", "");
		String pType = ServletRequestUtils.getStringParameter(request, "type", "");
		
		
		Media pMedia = new Media();
		pMedia.setId(pId);
		pMedia.setPath(pPath);
		pMedia.setDate(pDate);
		pMedia.setSize(pSize);
		pMedia.setType(pType);
		
		mediaService.addMedia(pMedia);
		
		ModelAndView model = new ModelAndView("add_media_test");	
		
		model.addObject("id", pId);
		model.addObject("path", pPath);
		model.addObject("date", pDate);
		model.addObject("size", pSize);
		model.addObject("type", pType);
	
		return model;
    }
	
	@RequestMapping("/updateMedia.do")
    public ModelAndView updateMedia(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		String pPath = ServletRequestUtils.getStringParameter(request, "path", "");
		String pDate = ServletRequestUtils.getStringParameter(request, "date", "");
		String pSize = ServletRequestUtils.getStringParameter(request, "size", "");
		String pType = ServletRequestUtils.getStringParameter(request, "type", "");
		
		
		Media pMedia = new Media();
		pMedia.setId(pId);
		pMedia.setPath(pPath);
		pMedia.setDate(pDate);
		pMedia.setSize(pSize);
		pMedia.setType(pType);
		
		mediaService.updateMedia(pMedia);
		
		ModelAndView model = new ModelAndView("update_media_test");	
		
		model.addObject("id", pId);
		model.addObject("path", pPath);
		model.addObject("date", pDate);
		model.addObject("size", pSize);
		model.addObject("type", pType);
	
		return model;
    }
	
	@RequestMapping("/deleteMedia.do")
    public ModelAndView deleteMedia(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int pId = ServletRequestUtils.getIntParameter(request, "id", 0);
		String pPath = ServletRequestUtils.getStringParameter(request, "path", "");
		String pDate = ServletRequestUtils.getStringParameter(request, "date", "");
		String pSize = ServletRequestUtils.getStringParameter(request, "size", "");
		String pType = ServletRequestUtils.getStringParameter(request, "type", "");

		Media pMedia = new Media();
		pMedia.setId(pId);
		pMedia.setPath(pPath);
		pMedia.setDate(pDate);
		pMedia.setSize(pSize);
		pMedia.setType(pType);
		
		mediaService.deleteMedia(pMedia);
		
		ModelAndView model = new ModelAndView("delete_media_test");	
		
		model.addObject("id", pId);
		model.addObject("path", pPath);
		model.addObject("date", pDate);
		model.addObject("size", pSize);
		model.addObject("type", pType);
	
		return model;
    }
}
