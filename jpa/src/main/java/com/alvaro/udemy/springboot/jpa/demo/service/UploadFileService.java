package com.alvaro.udemy.springboot.jpa.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {

	public String copy(MultipartFile file) throws IOException;

	public boolean delete(String filename);

	public void deleteAll();

	public void init() throws IOException;
}
