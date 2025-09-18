package com.project.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
	private String errorType;
    private String message;
    private int statusCode;
    
    public ErrorResponse(String errorType, String message, int statusCode) {
        this.errorType = errorType;
        this.message = message;
        this.statusCode = statusCode;
    }
}
