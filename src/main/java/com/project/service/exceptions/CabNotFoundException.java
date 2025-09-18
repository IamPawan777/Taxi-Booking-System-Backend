package com.project.service.exceptions;

public class CabNotFoundException extends RuntimeException {
	public CabNotFoundException(String msg) {
		super(msg);
	}
}
