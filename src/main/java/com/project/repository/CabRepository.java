package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.entity.Cab;

@Repository
public interface CabRepository extends JpaRepository<Cab, Float> {

	@Query("SELECT c FROM Cab c WHERE c.cabAvailable = true ORDER BY rand() LIMIT 1 ")
	Cab getAvailableCabRandom();
}
