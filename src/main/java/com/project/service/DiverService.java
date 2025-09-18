package com.project.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.dto.request.DriverRequest;
import com.project.dto.response.DriverResponse;
import com.project.entity.Customer;
import com.project.entity.Driver;
import com.project.repository.CustomerRepository;
import com.project.repository.DriverRepository;
import com.project.service.exceptions.CustomerNotFoundException;
import com.project.service.exceptions.DriverNotFoundException;
import com.project.transformer.CustomerTransformer;
import com.project.transformer.DriverTransformer;

import jakarta.transaction.Transactional;

@Service
public class DiverService {

	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;


	public DriverResponse addDriver(DriverRequest driverRequest) {
		Optional<Driver> existingDriver = driverRepository.findByEmailId(driverRequest.getEmailId());
		if(existingDriver.isPresent()) {
			
	        throw new RuntimeException("Email already exists. Please use a different email.");
	    }
		//DTO to Entity
		Driver driver = DriverTransformer.driverRequestToDriver(driverRequest, bCryptPasswordEncoder);
		Driver save = driverRepository.save(driver);
		// Entity to DTO
		DriverResponse driverResponse = DriverTransformer.driverToDriverResponse(save);
		return driverResponse;
		
	}
	

	public List<DriverResponse> getAll() {
		List<Driver> list = driverRepository.findAll();
		// Entity to DTO
		List<DriverResponse> driverResponse = new ArrayList<>();
		for(Driver driver : list) {
			driverResponse.add(DriverTransformer.driverToDriverResponse(driver));
		}
		return driverResponse;
	}
	
	

	public DriverResponse driverProfile(String emailId) {
		Optional<Driver> byEmailId = driverRepository.findByEmailId(emailId);
		if(byEmailId.isEmpty()) {
			 throw new DriverNotFoundException("Invalid Driver Id...");
		}
		Driver saveCustomer = byEmailId.get();
		// Entity -> DTO
		return DriverTransformer.driverToDriverResponse(saveCustomer);
	}


	public String updateDriverInfo(String emailId, int age, String name, String newPassword) {
		Optional<Driver> byEmailId = driverRepository.findByEmailId(emailId);
		if (byEmailId.isPresent()) {
			Driver driver = byEmailId.get();
			driver.setAge(age);
			driver.setName(name);
			String hashedPassword = bCryptPasswordEncoder.encode(newPassword);
			driver.setPassword(hashedPassword);
			
			driverRepository.save(driver);
			return "Update driver information Successfully..!";
		}
		else {
			return "E-mail id not found..Failed..!";
		}
	}

	@Transactional
	public String deleteDriver(String emailId) {
		Optional<Driver> byEmailId = driverRepository.findByEmailId(emailId);
		if(byEmailId.isPresent()) {
			driverRepository.deleteByEmailId(emailId);
			return "Deleted Successfully.";
		}
		else
			return "You Inserted Wrong Email Id.";
	}
	
	

	
}
