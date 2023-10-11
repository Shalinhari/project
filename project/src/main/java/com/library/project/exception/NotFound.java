package com.library.project.exception;

import org.springframework.stereotype.Component;

@Component
public class NotFound {
	private String message;
	protected NotFound() {
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
