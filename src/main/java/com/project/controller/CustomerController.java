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
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer APIs", description = "Add, Fetch and Check adult Customer ")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/add")
	@Operation(summary = "Add new Customer")
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
	public List<CustomerResponse> getAll(){
		return customerService.getAll();
	}
	
	
	@PreAuthorize("hasAnyRole('USER', 'RIDER')")
	@GetMapping("/get/profile-id/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable("id") String emailId) {
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
	public ResponseEntity<?> updatePassword(@RequestBody CustomerUpdateRequest updatePassword ) {
		String updateCustomer = customerService.updatePassword(updatePassword.getEmail(), updatePassword.getNewPassword());
	    return ResponseEntity.status(HttpStatus.OK).body(updateCustomer);
	}
	
	
	@PreAuthorize("hasRole('RIDER')")
	@DeleteMapping("/delete/{emailId}")
	public ResponseEntity<?> deleteByEmail(@PathVariable String emailId) {
		String deleteByEmail = customerService.deleteByEmail(emailId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteByEmail);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/adult")
	public List<CustomerResponse> getAdult(@RequestParam int age){
		return customerService.getAdult(age);
	}
}
