package com.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.entity.Admin;
import com.project.entity.Customer;
import com.project.entity.Driver;
import com.project.repository.AdminRepository;
import com.project.repository.CustomerRepository;
import com.project.repository.DriverRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DriverRepository driverRepository;
	
	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<Customer> customerEmailId = customerRepository.findByEmailId(emailId);
		if (customerEmailId.isPresent()) {
            return customerEmailId.get(); 
        }
		
		Optional<Driver> driverEmailId = driverRepository.findByEmailId(emailId);
        if (driverEmailId.isPresent()) {
            return driverEmailId.get();
        }
        
        Optional<Admin> adminEmailId = adminRepository.findByEmailId(emailId);
        if (adminEmailId.isPresent()) {
            return adminEmailId.get();
        }

        throw new UsernameNotFoundException("User not found with email: " + emailId);
	}
	
}
