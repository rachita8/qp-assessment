package com.store.management.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.store.management.exception.ResourceNotFoundException;
import com.store.management.model.Booking;
import com.store.management.model.Flight;
import com.store.management.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping(value="/api")
public class BookingController {
	
	@Autowired
	private BookingRepository brepo;
	
	
	//POST - http://localhost:9090/aerocom/api/booking
	@PostMapping("/booking")
	public String saveFlight(@RequestBody Booking booking) throws Exception
	{
		List<String> arr = booking.getSeatId();
		List<Booking> b = brepo.findBySeatIdIn(arr);
		if (b.isEmpty()) {
			booking.setNumberOfSeats(booking.getNumberOfSeats());
			booking.setTotalAmount(booking.getTotalAmount());
			brepo.save(booking);
			return "";
		}else
			throw new Exception("Seat is already booked"+b);
			
	}
		
	
	//Cancel booking
	//DELETE : http://localhost:9090/aerocom/api/booking/{passportNumber}
    @DeleteMapping("/booking/{passportNumber}")
    public Map<String,Boolean> deleteFlight(@PathVariable(value="passportNumber") String passportNumber)
     throws ResourceNotFoundException
     {
     Booking booking=brepo.findByPassportNumber(passportNumber).orElseThrow(() -> new ResourceNotFoundException
 			("No Booking done for this email: " +passportNumber));
    
    brepo.delete(booking);
    Map<String, Boolean> response=new HashMap<>();
    response.put("Booking Cancelled", Boolean.TRUE);
    return response;
    
    }
    //GET: http://localhost:9090/aerocom/api/booking/{email}
    @GetMapping("/booking/{email}")
	public List<Booking> getBookingData(@PathVariable(value="email") String email) {
    	
		return brepo.findByEmail(email);
    }
    @GetMapping("/booking/details/{passportNumber}")
	public Optional<Booking> getBookingDetail(@PathVariable(value="passportNumber") String passportNumber) {
    	
		return brepo.findByPassportNumber(passportNumber);
    }
    
    @GetMapping("/booking/details/byId/{id}")
	public Optional<Booking> getBookingDetailById(@PathVariable(value="id") String id) {
    	
		return brepo.findById(id);
    }
  //GET: http://localhost:9090/aerocom/api/latest 
    @GetMapping("/latest")
	public Booking getLatestBooking()
	{
		List<Booking> allBooking=brepo.findAll();  //invokes findAll() method of Mongo repository
		int temp=allBooking.size();
		Booking latest=allBooking.get(temp-1);
		return latest;

	}

}
