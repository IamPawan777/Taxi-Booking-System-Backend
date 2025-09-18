package com.project.transformer;

import com.project.dto.request.BookingRequest;
import com.project.dto.response.BookingResponse;
import com.project.entity.Booking;
import com.project.entity.Cab;
import com.project.entity.Customer;
import com.project.entity.Driver;
import com.project.entity.enums.Status;

public class BookingTransformer {

	public static Booking bookingRequestToBooking(BookingRequest bookingRequest, double perKmRate) {
		return Booking.builder()
				.pickUp(bookingRequest.getPickUp())
				.destination(bookingRequest.getDestination())
				.tripDistance(bookingRequest.getTripDistance())
				.tripStatus(Status.IN_PROGRESS)	
				.billAmount(bookingRequest.getTripDistance()*perKmRate)
				.build();		
	}

	public static BookingResponse bookingToBookingResponse(Booking booking, Customer customer, Cab cab, Driver driver) {
		return BookingResponse.builder()
				.pickUp(booking.getPickUp())
				.destination(booking.getDestination())
				.tripDistance(booking.getTripDistance())
				.tripStatus(booking.getTripStatus())
				.billAmount(booking.getBillAmount())
				.bookedAt(booking.getBookedAt())
				.lastUpdateAt(booking.getLastUpdateAt())
				.customerResponse(CustomerTransformer.customerTocustomerResponse(customer))
				.cabResponse(CabTransformer.cabToCabResponse(cab, driver))
				.build();
		
	}
	
	

}
