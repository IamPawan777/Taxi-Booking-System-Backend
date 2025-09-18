package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dto.request.CabRequest;
import com.project.dto.response.CabResponse;
import com.project.dto.response.CabResponse2;
import com.project.entity.Cab;
import com.project.entity.Driver;
import com.project.repository.CabRepository;
import com.project.repository.DriverRepository;
import com.project.service.exceptions.CabNotFoundException;
import com.project.service.exceptions.DriverNotFoundException;
import com.project.transformer.CabTransformer;

@Service
public class CabService {
	
	@Autowired
	CabRepository cabRepository;

	@Autowired
	DriverRepository driverRepository;

	public CabResponse registerCab(CabRequest cabRequest, int driverId) {
		Optional<Driver> byId = driverRepository.findById(driverId);	
		if(byId.isEmpty())
			throw new DriverNotFoundException("Invalid Driver ID.. This Driver Id not present.");		
		Driver driver = byId.get();
		
		Cab cab = CabTransformer.cabRequestToCab(cabRequest);
		driver.setCab(cab);													// driver class need
		
//		Cab saveCab = cabRepository.save(cab);
		Driver saveDriver = driverRepository.save(driver);					// save both "driver" and "cab" bcz of CaseCade.ALL
		return CabTransformer.cabToCabResponse(saveDriver.getCab(), saveDriver);
	}

	 
	
	public List<CabResponse2> getAllCabs() {
		List<Cab> allCab = cabRepository.findAll();
		// Entity to DTO
		List<CabResponse2> cabResponses = new ArrayList<>();
		for (Cab cab : allCab) {
			cabResponses.add(CabTransformer.cabToCabResponse2(cab));
		}
		return cabResponses;
	}



	public CabResponse2 getCabById(float cabId) {
		Optional<Cab> id = cabRepository.findById(cabId);
        if(id.isEmpty()) 
        	throw new CabNotFoundException("Cab not found with ID: " + cabId);
        return CabTransformer.cabToCabResponse2(id.get());
	}
}
