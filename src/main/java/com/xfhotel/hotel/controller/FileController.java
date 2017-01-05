package com.xfhotel.hotel.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/file")
public class FileController {

	@Autowired
	FileService fileService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Message upload(MultipartFile file, HttpServletRequest request) {

		if (file != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(request.getSession().getServletContext().getRealPath("/"));
			String fullPath = fileService.saveFile(file, sb.toString());
			if (fullPath != null)
				return new Message(Constants.MESSAGE_SUCCESS_CODE, fullPath);
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "上传失败");
	}

	@RequestMapping(value = "/multiUpload", method = RequestMethod.POST)
	public @ResponseBody Message multiUpload(MultipartHttpServletRequest  request) {
		  if (!ServletFileUpload.isMultipartContent(request)) {
	            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
	        }

	        ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
	        
	        try {
	            List<FileItem> items = uploadHandler.parseRequest(request);
	            for (FileItem item : items) {
	                if (!item.isFormField()) {
	                        File file = new File(request.getSession().getServletContext().getRealPath("/")+"images/", item.getName());
	                        item.write(file);
	                        JSONObject jsono = new JSONObject();
	                        jsono.put("name", item.getName());
	                        jsono.put("size", item.getSize());
	                        jsono.put("url", "UploadServlet?getfile=" + item.getName());
	                        jsono.put("thumbnail_url", "UploadServlet?getthumb=" + item.getName());
	                        jsono.put("delete_url", "UploadServlet?delfile=" + item.getName());
	                        jsono.put("delete_type", "GET");
	                        System.out.println(jsono.toString());
	                }
	            }
	        } catch (FileUploadException e) {
	                throw new RuntimeException(e);
	        } catch (Exception e) {
	                throw new RuntimeException(e);
	        } finally {
	        }
		return new Message(Constants.MESSAGE_ERR_CODE, "wu");
	}

	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	public @ResponseBody Message uploadAvatar(String avatarData, MultipartFile avatarFile, HttpServletRequest request) {
		if (avatarFile != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(request.getSession().getServletContext().getRealPath("/"));
			JSONObject data = new JSONObject(avatarData);
			String fullPath = fileService.cropImage(avatarFile, data, sb.toString());
			if (fullPath != null)
				return new Message(Constants.MESSAGE_SUCCESS_CODE, fullPath);
		}
		return new Message(Constants.MESSAGE_ERR_CODE, "上传失败");
	}
}
