package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CabRequest {
	private String cabNumber;
	private String cabModel;
	private double cabPerKmRate;
}
