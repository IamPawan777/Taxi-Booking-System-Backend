package com.project.dto.request;

import lombok.Data;


@Data
public class DriverInfoUpdateRequest {
	private String name;
	private int age;
	private String password;
}
