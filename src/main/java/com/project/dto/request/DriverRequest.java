package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverRequest {
	private String name;
	private int age;
	private String emailId; 
	private String password;		//
	private String role;			//
}
