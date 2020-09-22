package com.nokia.ee.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface DocumentStorageService {
	String storeFile(MultipartFile file, Integer userId, String docType);

	Resource loadFileAsResource(String fileName);

	String getDocumentName(Integer userId, String docType);

}
