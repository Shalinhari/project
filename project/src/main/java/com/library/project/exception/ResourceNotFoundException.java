package com.library.project.exception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String reason) {
		super(reason);
	}

}
