package com.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.project.dto.request.BookingRequest;
import com.project.dto.response.BookingResponse;
import com.project.dto.response.DriverResponse;
import com.project.entity.Booking;
import com.project.entity.Cab;
import com.project.entity.Customer;
import com.project.entity.Driver;
import com.project.entity.enums.Status;
import com.project.repository.BookingRepository;
import com.project.repository.CabRepository;
import com.project.repository.CustomerRepository;
import com.project.repository.DriverRepository;
import com.project.service.exceptions.BookingNotFoundException;
import com.project.service.exceptions.CabUnAvailableException;
import com.project.service.exceptions.CustomerNotFoundException;
import com.project.transformer.BookingTransformer;
import com.project.transformer.DriverTransformer;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class BookingService {

	@Value("${spring.mail.username}") 
	private String sender;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CabRepository cabRepository;
	
	@Autowired
	DriverRepository driverRepository;

	@Autowired
	JavaMailSender javaMailSender;

	public BookingResponse bookCab(BookingRequest bookingRequest, int customerId) {
		Optional<Customer> byId = customerRepository.findById(customerId);
		if(byId.isEmpty())
			throw new CustomerNotFoundException("Customer Not Found. Add This Customer First...");
		Customer customer = byId.get();
		
		
		Cab availableCabRandom = cabRepository.getAvailableCabRandom();
		if(availableCabRandom == null) 
			throw new CabUnAvailableException("Sorry No Cab Available");
		Booking requestToBooking = BookingTransformer.bookingRequestToBooking(bookingRequest, availableCabRandom.getCabPerKmRate());
		Booking saveBooking = bookingRepository.save(requestToBooking);
		requestToBooking.setCab(availableCabRandom); 
		availableCabRandom.setCabAvailable(false);
		
		
		customer.getBooking().add(saveBooking);
		Driver driver  = driverRepository.getDriverByCabId(availableCabRandom.getCab_id());
		driver.getBooking().add(saveBooking);
		
		Customer saveCustomer = customerRepository.save(customer);
		Driver saveDriver = driverRepository.save(driver);
		
		sendEmail(saveCustomer, availableCabRandom, saveBooking, saveDriver);
		
		return BookingTransformer.bookingToBookingResponse(saveBooking, saveCustomer, availableCabRandom, saveDriver);
	}
	
	
	
//	private void sendEmail(Customer customer, Cab cab) {
//		
//		String msg = "Congratulation!! '"+customer.getName()+"' your cab '"+cab.getCabNumber()+"' has been booked.";
//		
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//		mailMessage.setFrom("springtester7@gmail.com");
//		mailMessage.setTo(customer.getEmailId());
//		mailMessage.setSubject("Cab Booked. Successfully!");
//		mailMessage.setText(msg);
////		mailMessage.notify();
//		
//		javaMailSender.send(mailMessage);		
//	}
	
	
	private void sendEmail(Customer customer, Cab cab, Booking booking, Driver driver) {
	    try {
	        // Create a MimeMessage which supports HTML
	        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8"); // true indicates multipart message

	        String htmlMsg = """
	            <!DOCTYPE html>
	            <html>
	            <head>
	                <meta charset="UTF-8">
	                <title>Cab Booking Confirmation</title>
	            </head>
	            <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
	                <p>Hey, <strong>%s</strong></p>
	                
	                <p>Congratulations! Your cab has been successfully booked.</p>
	                
	                <p>
	                    You have successfully booked the cab <strong>%s</strong> for your journey.
	                    The booking ID <strong>%s</strong> was made under the name <strong>%s</strong>. <br>
	                    Your cab will arrive at your pickup location shortly. Please be ready at the specified time.
	                </p>
	                
	                <p>Your cab details are as follows:<br>
	                <strong>Booking ID:</strong> %s<br>
	                <strong>Cab Number:</strong> %s<br>
	                <strong>Driver Name:</strong> %s<br>
	   
	                <!-- Add more cab details as needed -->
	                </p>
	                
	                <p>For any queries related to your booking, please contact our customer support.</p>
	                
	                <p>Warm Regards,<br>
	                <strong>The Texi Booking Team</strong></p>
	                
	                <hr style="border: none; border-top: 1px solid #eee;">
	                <p style="font-size: 0.9em; color: #777;">
	                    <em>This is a computer-generated email and does not require a signature.</em>
	                </p>
	            </body>
	            </html>
	            """.formatted(
	            	customer.getName(), 
	                cab.getCabNumber(), 
	                booking.getBooking_id(),
	                customer.getName(), 
	                booking.getBooking_id(),
	                cab.getCabNumber(),
	                driver.getName()
	            );

	        // Set email details
	        helper.setFrom("springtester7@gmail.com");
	        helper.setTo(customer.getEmailId());
	        helper.setSubject("Cab Booked Successfully!");
	        
	        helper.setText(htmlMsg, true);

	        // Send the email
	        javaMailSender.send(mimeMessage);
	        
	    } catch (MessagingException e) {
	        e.printStackTrace(); 
	    }
	}

	
	public String cancelBooking(int bookingId) {
        // 1. Find the booking by its ID
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
        }
        Booking booking = optionalBooking.get();

        // 2. Check if it's already cancelled
        if(booking.getTripStatus() == Status.CANCELLED)
            return "Booking was already CANCELLED. Wrong Rider Id.";        
        if(booking.getTripStatus() == Status.COMPLETED)
            return "Booking was COMPLETED. Wrong Rider Id.";

        // 4. Update ONLY the booking status to CANCELLED
        booking.setTripStatus(Status.CANCELLED);
        bookingRepository.save(booking);
        
        Cab cab = booking.getCab();
        if (cab != null) {
            cab.setCabAvailable(true);
            cabRepository.save(cab);
        }
        return "Booking with ID '" + bookingId + "' has been cancelled successfully.";
    }



	public String completeBooking(int bookingId) {
		 // 1. Find the booking by its ID
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
        if(optionalBooking.isEmpty()) {
            throw new BookingNotFoundException("Booking with ID " + bookingId + " not found.");
        }
        Booking booking = optionalBooking.get();

        if(booking.getTripStatus() == Status.CANCELLED) {
            return "Booking was CANCELLED. Wrong Rider Id ...!";
        }
        
        if(booking.getTripStatus() == Status.COMPLETED) {
            return "Booking was already COMPLETED. Wrong Rider Id ...!";
        }

        booking.setTripStatus(Status.COMPLETED);
        bookingRepository.save(booking);
        
        Cab cab = booking.getCab();
        if (cab != null) {
            cab.setCabAvailable(true);
            cabRepository.save(cab);
        }
        return "Booking with ID '" + bookingId + "' has been Completed. Rider Reach there destination.";
	}

}
