package com.nokia.ee.demo.exception;

public class DocumentStorageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DocumentStorageException(String message) {
		super(message);
	}

	public DocumentStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
