package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.request.BookingRequest;
import com.project.dto.response.BookingResponse;
import com.project.dto.response.DriverResponse;
import com.project.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/booking")
@Tag(name = "Booking API's", description = "Booking Status")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@PreAuthorize("hasRole('RIDER')")
//	@Operation(security = { @SecurityRequirement(name = "bearerAuth") })
	@PostMapping("/book/customer-id/{customerId}")
	public BookingResponse bookCab(@RequestBody BookingRequest bookingRequest, @PathVariable("customerId") int customerId) {
		return bookingService.bookCab(bookingRequest, customerId);
	}
	
	
	
	@PreAuthorize("hasRole('RIDER')")
	@PostMapping("/cancel/customer/bookingId/{bookingId}")
    public String cancelBooking(@PathVariable int bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
	
	
	@PreAuthorize("hasRole('DRIVER')")
	@PostMapping("/complete/driver/bookingId/{bookingId}")
    public String completeBooking(@PathVariable int bookingId) {
        return bookingService.completeBooking(bookingId);
    }
	
	
//	@GetMapping("/get/all")
//	public List<BookingResponse> getAll(){
//		return bookingService.getAll();
//	}
	
	
//	@GetMapping("/get/all")	
//	public ResponseEntity<List<BookingResponse>> getAllBookings() {
//		try {
//			List<BookingResponse> bookings = bookingService.getAll();
//			return ResponseEntity.ok(bookings);
//		} catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}

}
