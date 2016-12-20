package com.xfhotel.hotel.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value = "/uploadAvatar", method = RequestMethod.POST)
	public @ResponseBody Message uploadAvatar(String avatarData,MultipartFile avatarFile,HttpServletRequest request){
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
