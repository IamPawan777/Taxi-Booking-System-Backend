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
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.request.DriverInfoUpdateRequest;
import com.project.dto.request.DriverRequest;
import com.project.dto.response.DriverResponse;
import com.project.service.DiverService;
import com.project.service.exceptions.DriverNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/driver")
@Tag(name = "🚖 Driver API", description = "Add, Delete, Update and check Profile of Driver APIs")
public class DriverController {
	
	@Autowired
	DiverService driverService;
	
	@PostMapping("/add")
	@Operation(summary = "1-Registers a new driver in the system", description = "Public authetication")
	public ResponseEntity<?> addDriver(@RequestBody DriverRequest driverRequest) {
		try {
			DriverResponse driver = driverService.addDriver(driverRequest);
			return ResponseEntity.status(HttpStatus.OK).body(driver);
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	    }
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/all")
	@Operation(summary = "5-Get all driver which already stored by admin", description = "Requires Admin role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public List<DriverResponse> getAll(){
		return driverService.getAll();
	}
	
	@PreAuthorize("hasRole('DRIVER')")
	@GetMapping("/get/profile-id/{id}")
	@Operation(summary = "2-Check complete driver profile by driver", description = "Requires Driver role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> driverProfile(@PathVariable("id") String emailId){
		try {
			DriverResponse response = driverService.driverProfile(emailId);
			return ResponseEntity.ok(response);
		}
		catch (DriverNotFoundException e) {
			throw e;
		}
	}
	
	@PreAuthorize("hasRole('DRIVER')")
	@PutMapping("/update/{emailId}")
	@Operation(summary = "3-Update driver information in the app by driver", description = "Requires Driver role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> updateDriverInfo(@PathVariable String emailId, @RequestBody DriverInfoUpdateRequest updateDriverInfo ){
		String driverInfo = driverService.updateDriverInfo(emailId, updateDriverInfo.getAge(), updateDriverInfo.getName(), updateDriverInfo.getPassword());
		return ResponseEntity.status(HttpStatus.OK).body(driverInfo);
	}
	
	@PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
	@DeleteMapping("/delete/{emailId}")
	@Operation(summary = "4-Delete driver account in the app by admin & driver", description = "Requires Driver, Admin role")
    @SecurityRequirement(name = "bearerAuth") // ✅ JWT required
	public ResponseEntity<?> deleteDriver(@PathVariable String emailId){
		String deleteDriver = driverService.deleteDriver(emailId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(deleteDriver);
	}
	
	
	
}
