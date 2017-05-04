package com.xfhotel.hotel.service;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String saveFile(MultipartFile file,String path);
	public boolean removeFile(String path);
	public String cropImage(MultipartFile image,JSONObject imageData,String webRoot);
}
