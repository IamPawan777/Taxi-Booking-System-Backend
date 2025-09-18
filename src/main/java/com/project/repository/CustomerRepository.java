package com.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public List<Customer> findByAgeGreaterThan(int age);

	public Optional<Customer> findByEmailId(String oldEmailId);

	public void deleteByEmailId(String emailId);
	
}
