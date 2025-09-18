package com.project.dto.request;

import lombok.Data;

@Data
public class CustomerUpdateRequest {
	private String email;
	private String newPassword;
}
