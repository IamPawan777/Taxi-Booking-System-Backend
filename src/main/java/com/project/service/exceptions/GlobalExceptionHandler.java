package com.project.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.dto.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CUSTOMER_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(DriverNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDriverNotFoundException(DriverNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("DRIVER_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	
	@ExceptionHandler(CabNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCabNotFoundException(CabNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse("CAB_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception exception){
		return ResponseEntity.internalServerError().body("Access Denied...!");
	}
}
