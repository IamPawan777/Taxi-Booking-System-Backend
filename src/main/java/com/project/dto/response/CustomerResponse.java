package com.project.dto.response;

import com.project.entity.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {
	private String name;
	private int age;
	private String emailId;
	private Gender gender;
}
