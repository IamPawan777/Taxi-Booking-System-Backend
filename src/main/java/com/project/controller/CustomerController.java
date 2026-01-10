package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.request.CustomerRequest;
import com.project.dto.request.CustomerUpdateRequest;
import com.project.dto.response.CustomerResponse;
import com.project.service.CustomerService;
import com.project.service.exceptions.CustomerNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(name = "‍‍👨‍👩‍👧 Customer API", description = "Add, Fetch and Check Customer related APIs ")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	@Operation(summary = "1-Add new Customer", description = "Public endpoint - No authentication required")
	public ResponseEntity<?> addCustomer(@RequestBody CustomerRequest customerRequest) {
		try {
			CustomerResponse customer = customerService.addCustomer(customerRequest);
	        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
		} 
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/all")
	@Operation(summary = "6-View all registered customers by admin", description = "Requires ADMIN role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public List<CustomerResponse> getAll(){
		return customerService.getAll();
	}
	
	
	@PreAuthorize("hasAnyRole('USER', 'RIDER')")
	@GetMapping("/get/profile-id/{emailId}")
	@Operation(summary = "2-Get customer profile using email id by rider ", description = "Requires USER or RIDER role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> getCustomer(@PathVariable("emailId") String emailId) {
		try {
			CustomerResponse response = customerService.getCustomer(emailId);
			return ResponseEntity.ok(response);
		} 
		catch (CustomerNotFoundException e) {
			// This will be handled by the GlobalExceptionHandler
			throw e;
		}
	}
	
	
	@PreAuthorize("hasRole('RIDER')")
	@PutMapping("/update")
	@Operation(summary = "3-Customer allow to update their own information rider", description = "Requires RIDER role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> updatePassword(@RequestBody CustomerUpdateRequest updatePassword ) {
		String updateCustomer = customerService.updatePassword(updatePassword.getEmail(), updatePassword.getNewPassword());
	    return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);
	}
	
	
	@PreAuthorize("hasRole('RIDER')")
	@DeleteMapping("/delete/{emailId}")
	@Operation(summary = "4-Delete customer account using email id by rider", description = "Requires RIDER role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> deleteByEmail(@PathVariable String emailId) {
		String deleteByEmail = customerService.deleteByEmail(emailId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteByEmail);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/adult")
	@Operation(summary = "5-Filters customers whose age is 18 or above by admin", description = "Requires ADMIN role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public List<CustomerResponse> getAdult(@RequestParam int age){
		return customerService.getAdult(age);
	}
}
