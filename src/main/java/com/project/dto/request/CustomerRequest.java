package com.project.dto.request;

import com.project.entity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
	private String name;
	private int age;
	private String emailId;
	private String password;
	private String role;
	private Gender gender;
}
