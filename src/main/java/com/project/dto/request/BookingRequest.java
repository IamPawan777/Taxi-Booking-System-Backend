package com.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingRequest {
	private String pickUp;
	private String destination;
	private float tripDistance;
}
