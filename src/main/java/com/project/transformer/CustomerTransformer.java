package com.project.transformer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.dto.request.CustomerRequest;
import com.project.dto.response.CustomerResponse;
import com.project.entity.Customer;


// this class host entity to DTO and vice versa things...
public class CustomerTransformer {
	
	//DTO -> Entity
	public static Customer customerRequestToCustomer(CustomerRequest customerRequest, BCryptPasswordEncoder bCryptPasswordEncoder) {
//		Customer customer = new Customer();
//		customer.setName(customerRequest.getName());
//		customer.setAge(customerRequest.getAge());
//		customer.setEmailId(customerRequest.getEmailId());
//		customer.setGender(customerRequest.getGender());
//		return customer;
		return Customer.builder()
				.name(customerRequest.getName())
				.age(customerRequest.getAge())
				.emailId(customerRequest.getEmailId())
				.password(bCryptPasswordEncoder.encode(customerRequest.getPassword()))
				.role(formatRole(customerRequest.getRole()))
				.gender(customerRequest.getGender())
				.build();
	}
	
	
	// Entity -> DTO
	public static CustomerResponse customerTocustomerResponse(Customer customer) {
//		CustomerResponse customerResponse = new  CustomerResponse();
//		customerResponse.setName(customer.getName());
//		customerResponse.setAge(customer.getAge());
//		customerResponse.setEmailId(customer.getEmailId());
//		return customerResponse;
		CustomerResponse customerResponse = CustomerResponse.builder()
				.name(customer.getName())
				.age(customer.getAge())
				.emailId(customer.getEmailId())
				.build();
		return customerResponse;

	}
	
	 // Role formatting utility method
    private static String formatRole(String role) {
//        if (role == null || role.trim().isEmpty()) {
            return "ROLE_RIDER"; // Default role
//        }
        
//        String formattedRole = role.trim().toUpperCase();
//        if (!formattedRole.startsWith("ROLE_")) {
//            formattedRole = "ROLE_" + formattedRole;
//        }
//        return formattedRole;
    }
}
