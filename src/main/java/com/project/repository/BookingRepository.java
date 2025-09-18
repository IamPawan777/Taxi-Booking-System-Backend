package com.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@EntityGraph(attributePaths = { "cab" })
	Optional<Booking> findById(Integer id);
}
