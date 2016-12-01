package com.xfhotel.hotel.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xfhotel.hotel.service.FileService;

/**
 * 
 * @author Ming
 *
 */
@Service
public class FileServiceImpl implements FileService {

	@Override
	public boolean removeFile(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveFile(MultipartFile file, String path) {
		String type = null;// 鏂囦欢绫诲瀷
		String fileName = file.getOriginalFilename();// 鏂囦欢鍘熷悕绉�
		type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
				: null;
		if (type != null) {
			if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
					|| "JPG".equals(type.toUpperCase())) {
				UUID uuid = UUID.randomUUID();
				StringBuffer sb = new StringBuffer();
				sb.append(uuid).append(".").append(type);
				File dir = new File(path + "images/");
				if (!dir.exists()) {
					dir.mkdir();
				}
				try {
					file.transferTo(new File(path+"images/" + sb.toString()));
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

}
