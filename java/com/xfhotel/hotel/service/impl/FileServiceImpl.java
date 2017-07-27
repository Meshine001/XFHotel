package com.xfhotel.hotel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xfhotel.hotel.service.FileService;
import com.xfhotel.hotel.support.FileUtil;
import com.xfhotel.hotel.support.ImageUtils;

/**
 * 
 * @author Ming
 *
 */
@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = Logger.getLogger(FileServiceImpl.class.getSimpleName());
	
	@Override
	public boolean removeFile(String path) {
		// TODO Auto-generated method stub
		return false;
	}
	
		
	@Override
	public String saveFile(MultipartFile file, String path) {
		String type = null;// 
		String fileName = file.getOriginalFilename();// 
		type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
				: null;
		if (type != null) {
			if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
					|| "JPG".equals(type.toUpperCase())) {
				UUID uuid = UUID.randomUUID();
				StringBuffer sb = new StringBuffer();
				sb.append(uuid).append(".").append(type);
				System.out.println(new File(path).getParent());
				String rPath = new File(path).getParent();
				File dir = new File(path + "/images/");
				if (!dir.exists()) {
					dir.mkdir();
				}
				try {
					file.transferTo(new File(path + "/images/" + sb.toString()));
					return new StringBuffer(sb.toString()).toString();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
	}


	@Override
	public String cropImage(MultipartFile image, JSONObject imageData, String webRoot) {
		try {
			File source = new File(FileUtil.getTempDir(webRoot)+image.getOriginalFilename());
			image.transferTo(source);
			File target = new File(FileUtil.getImageDir(webRoot)+FileUtil.getUUName()+".jpg");
			int x = imageData.getInt("x");
			int y = imageData.getInt("y");
			int w = imageData.getInt("width");
			int h = imageData.getInt("height");
			ImageUtils.crop(source, target, x, y, w, h);
			System.out.println(target.getAbsolutePath());
			return target.getName();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


}
