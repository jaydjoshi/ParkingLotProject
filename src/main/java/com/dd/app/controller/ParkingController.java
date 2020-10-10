package com.dd.app.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dd.app.model.Greeting;
import com.dd.app.model.Vehicle;
import com.dd.app.service.ParkingService;
import com.sun.net.httpserver.Authenticator.Success;

@RestController
public class ParkingController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired 
	private ParkingService parkingService;

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	/**
	 * 
	 * @param vehicle
	 * @param parkingLotId
	 * @param parkingUnitId
	 * @return
	 */
	@PostMapping("/park/{parkingLotId}/{parkingUnitId}")
	public ResponseEntity<Success> park(@RequestBody Vehicle vehicle, @PathVariable int parkingLotId,@PathVariable int parkingUnitId){
		
		boolean res = parkingService.park(vehicle, parkingLotId, parkingUnitId);
		if(res)
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	/**
	 * 
	 * @param vehicle
	 * @param parkingLotId
	 * @param parkingUnitId
	 * @return
	 */
	@PostMapping("/unpark/{parkingLotId}/{parkingUnitId}")
	public Integer unpark(@RequestBody Vehicle vehicle, @PathVariable int parkingLotId,@PathVariable int parkingUnitId){
		
		int amount = parkingService.unpark(vehicle, parkingLotId, parkingUnitId);
		return amount;
		
	}
	
}