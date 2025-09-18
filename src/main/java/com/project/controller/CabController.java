package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.request.CabRequest;
import com.project.dto.response.CabResponse;
import com.project.dto.response.CabResponse2;
import com.project.service.CabService;
import com.project.service.exceptions.CabNotFoundException;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cab")
@Tag(name = "Cub APIs", description = "Check Cab Api ")
public class CabController {
	
	@Autowired
	CabService cabService;
	
	
	@PreAuthorize("hasRole('DRIVER')")
	@PostMapping("/register/driverId/{driverId}")
	public CabResponse registerCab(@RequestBody CabRequest cabRequest, @PathVariable("driverId") int driverId) {
		return cabService.registerCab(cabRequest, driverId);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/all")
	public List<CabResponse2> getAllCabs() {
		return cabService.getAllCabs();
	}
	
	
	@PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
	@GetMapping("/get/cab-id/{cabId}")
	public ResponseEntity<?> getCabById(@PathVariable float cabId) {
		try {
			CabResponse2 response = cabService.getCabById(cabId);
			return ResponseEntity.ok(response);
		} 
		catch (CabNotFoundException e) {
			throw e;
		}        
    }
	
	

}
