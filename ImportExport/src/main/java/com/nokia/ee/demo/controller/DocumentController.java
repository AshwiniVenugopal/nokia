package com.nokia.ee.demo.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nokia.ee.demo.Model.UploadFileResponse;
import com.nokia.ee.demo.service.DocumentStorageService;

@Repository
@Component
@RestController
public class DocumentController {

	@Autowired
	private DocumentStorageService documneStorageService;

	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam("userId") Integer userId, @RequestParam("docType") String docType) {
		String fileName = documneStorageService.storeFile(file, userId, docType);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam("userId") Integer userId,
			@RequestParam("docType") String docType, HttpServletRequest request) throws IOException {
		String fileName = documneStorageService.getDocumentName(userId, docType);
		Resource resource = null;
		if (fileName != null && !fileName.isEmpty()) {
			try {
				resource = documneStorageService.loadFileAsResource(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}

			String contentType = null;
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

			if (contentType == null) {
				contentType = "application/octet-stream";
			}
			return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
