package com.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CabResponse2 {
	private String cabNumber;
	private String cabModel;
	private double cabPerKmRate;
}
