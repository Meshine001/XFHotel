package com.xfhotel.hotel.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xfhotel.hotel.common.Constants;
import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.support.FileMeta;
import com.xfhotel.hotel.support.Message;

@Controller
@RequestMapping("/file")
public class FileController {
	
	private String getUUIDName(String contentType){
		UUID uuid = UUID.randomUUID();
		return uuid.toString()+"."+"jpg";
	}
	/***************************************************
	 * URL: /rest/controller/upload upload(): receives files
	 * 
	 * @param request
	 *            : MultipartHttpServletRequest auto passed
	 * @param response
	 *            : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		String basePath = request.getSession().getServletContext().getRealPath("/");
		
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		
		FileMeta fileMeta = null;
		
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		while (itr.hasNext()) {

			mpf = request.getFile(itr.next());
//			System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

			fileMeta = new FileMeta();
			fileMeta.setFileName(getUUIDName(mpf.getContentType()));
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				fileMeta.setBytes(mpf.getBytes());

				FileCopyUtils.copy(mpf.getBytes(),
						new FileOutputStream(basePath + "/images/" + fileMeta.getFileName()));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			files.add(fileMeta);
		}
		return files;
	}

	// =======================================================
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
	public @ResponseBody Message multiUpload(MultipartHttpServletRequest request) {
		if (!ServletFileUpload.isMultipartContent(request)) {
			throw new IllegalArgumentException(
					"Request is not multipart, please 'multipart/form-data' enctype for your form.");
		}

		ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());

		try {
			List<FileItem> items = uploadHandler.parseRequest(request);
			for (FileItem item : items) {
				if (!item.isFormField()) {
					File file = new File(request.getSession().getServletContext().getRealPath("/") + "images/",
							item.getName());
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
