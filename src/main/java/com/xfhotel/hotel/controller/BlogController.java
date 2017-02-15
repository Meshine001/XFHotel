package com.xfhotel.hotel.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.entity.Blog;
import com.xfhotel.hotel.entity.Customer;
import com.xfhotel.hotel.entity.Feature;
import com.xfhotel.hotel.service.BlogService;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.support.BlogUploadUtil;
import com.xfhotel.hotel.support.Message;
import com.xfhotel.hotel.support.PageResults;

@Controller
@RequestMapping("/admin/blog")
public class BlogController {
	@Autowired
	BlogService blogService;
	@Autowired
	FileService fileService;
	
	@RequestMapping(value = "/create")
	public String createBlog() {
		return "/admin/blog/create_blog";
	}
	
	@RequestMapping(value = "/publish")
	public @ResponseBody long publishBlog(HttpServletRequest request, String title, String content) {
		String basepath = request.getSession().getServletContext().getRealPath("/");
		Blog blog = new Blog();
		blog.setTitle(title);
		blog.setStatus("0");
		int status = blogService.publish(blog, content, basepath);
		return blog.getId();
	}
	
	
	@RequestMapping(value = "/edit")
	public String editBlog(HttpServletRequest request,String id) {
		request.setAttribute("id", id);
		return "/admin/blog/edit_blog";
	}
	
	@RequestMapping(value = "/load_blog")
	public @ResponseBody Map loadBlog(HttpServletRequest request,Long id) {
		String path = request.getSession().getServletContext().getRealPath("/");
		Blog blog = blogService.find(id);
		path += "blog\\" + blog.getPath();
		Map map = blog.toMap();
		StringBuffer content = new StringBuffer();
		FileReader fr;
		try {
			fr = new FileReader(path);
			BufferedReader br=new BufferedReader(fr);
			String str;
			while( ( str=br.readLine())!=null){
				content.append(str);
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("content", content.toString());
		return map;
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody int update(HttpServletRequest request,Long id, String title, String content) {
		String basepath = request.getSession().getServletContext().getRealPath("/");
		Blog blog = blogService.find(id);
		blog.setTitle(title);
		int status = blogService.update(blog, content, basepath);
		return status;
	}
	
	@RequestMapping(value = "/show")
	public String showBlog(String id) {
		return "/admin/blog/show_blog";
	}
	
	@RequestMapping(value = "/list")
	public String listBlog() {
		return "/admin/blog/list";
	}
	
	@RequestMapping(value = "get_blogs", method = RequestMethod.POST)
	public @ResponseBody PageResults<Blog> getBlogs (int page){
		return blogService.list(page);
	}
	
	@RequestMapping(value = "delete_blog", method = RequestMethod.POST)
	public @ResponseBody String deleteBlog (long id){
		blogService.delete(id);
		return "/";
	}
	
	@RequestMapping(value = "change_status", method = RequestMethod.POST)
	public @ResponseBody String change_Blog (long id, String status){
		Blog blog = blogService.find(id);
		blog.setStatus(status);
		blogService.change(blog);
		return "/";
	}
	
	

	
	@RequestMapping(value = "upload_blog_pic", method = RequestMethod.POST)
	public @ResponseBody String upload_pic (MultipartFile myFileName, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		if (myFileName != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(request.getSession().getServletContext().getRealPath("/"));
			String fullPath = fileService.saveFile(myFileName, sb.toString());
			return Constants.Host_Address+"images/"+fullPath;
		}
		return "";
	}
}
