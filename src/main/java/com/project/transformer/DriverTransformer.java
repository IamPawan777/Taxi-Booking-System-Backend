package com.project.transformer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.dto.request.DriverRequest;
import com.project.dto.response.DriverResponse;
import com.project.entity.Driver;

public class DriverTransformer {

	public static Driver driverRequestToDriver(DriverRequest driverRequest, BCryptPasswordEncoder bCryptPasswordEncoder) {
		return Driver.builder()
				.name(driverRequest.getName())
				.age(driverRequest.getAge())
				.emailId(driverRequest.getEmailId())
				.password(bCryptPasswordEncoder.encode(driverRequest.getPassword()))
				.role(formatRole(driverRequest.getRole()))
				.build();
	}

	public static DriverResponse driverToDriverResponse(Driver save) {
		return DriverResponse.builder()
				.driver_id(save.getDriver_id())
				.name(save.getName())
				.age(save.getAge())
				.emailId(save.getEmailId())
				.build();
	}
	
    private static String formatRole(String role) {
//        if (role == null || role.trim().isEmpty()) {
            return "ROLE_DRIVER"; // Default role
//        }
        
//        String formattedRole = role.trim().toUpperCase();
//        if (!formattedRole.startsWith("ROLE_")) {
//            formattedRole = "ROLE_" + formattedRole;
//        }
//        return formattedRole;
    }
	
}
