package com.project.service.exceptions;

public class CustomerNotFoundException extends RuntimeException {
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
