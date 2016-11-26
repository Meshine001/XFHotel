package com.xfhotel.hotel.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String saveFile(MultipartFile file,String path);
	public boolean removeFile(String path);
}
