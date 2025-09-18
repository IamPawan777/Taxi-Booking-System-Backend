package com.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Cab {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private float cab_id;
	
	@Column(unique = true)
	private String cabNumber;
	private String cabModel;
	private double cabPerKmRate;
	private boolean cabAvailable;
	
//	  @OneToOne(fetch = FetchType.LAZY)
//	    @JoinColumn(name = "driver_id")
//	    private Driver driver;
}
