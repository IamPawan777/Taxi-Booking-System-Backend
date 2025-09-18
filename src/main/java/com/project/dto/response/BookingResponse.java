package com.project.dto.response;

import java.sql.Date;
import java.time.LocalTime;

import com.project.entity.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingResponse {
	private String pickUp;
	private String destination;
	private float tripDistance;
	
	private Status tripStatus;
	private double billAmount;			// tripDistance * perKmRate
	private LocalTime bookedAt;
	private Date lastUpdateAt;
	
	private CustomerResponse customerResponse;
	private CabResponse cabResponse;

}
