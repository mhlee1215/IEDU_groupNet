package com.iedu.web;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	
	@RequestMapping(value="/mediaUpload.do", method=RequestMethod.POST, consumes = {"multipart/mixed"})
    public @ResponseBody String handleFileUpload( 
            @RequestParam("file") MultipartFile file11){
            String name = "test11";
        if (!file11.isEmpty()) {
            try {
                byte[] bytes = file11.getBytes();
                BufferedOutputStream stream = 
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	@RequestMapping(value="/mediaUpload2.do", method=RequestMethod.POST, consumes = {"multipart/mixed"})
    public @ResponseBody String handleFileUpload(
    		@RequestParam(value = "textMessage",required=false) String message,
            @RequestParam(value = "file", required = false) MultipartFile file,
            MultipartHttpServletRequest request, ModelAndView modelAndView) throws IllegalStateException, IOException{    
			
		System.out.println("message:"+message);
		System.out.println("name:"+file.getOriginalFilename());
		System.out.println("size:"+file.getSize());
		System.out.println(request.getContextPath());
	
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		UUID uid = UUID.randomUUID();
		
		String id = timeStamp+"_"+uid;
		File convFile = new File( request.getSession().
				getServletContext().getRealPath("/assets/"), id);
		file.transferTo(convFile);
		
		System.out.println(convFile.getAbsolutePath());
		
		return "uploaded files :" + id;
    }
	
	
}
