package com.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/orderservice")
public class OrderServiceRestController {
	
	@Autowired
	private OrderServiceRepository orderServiceRepository;
	
	@GetMapping(value="/get")
	public ResponseEntity<Object> findAll(){
		 try {
			 List<OrderService> orderServiceList =  orderServiceRepository.findAll();
			 return new ResponseEntity<>(orderServiceList, HttpStatus.OK);
		 }catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		 }
		
		
	}
	
	@PostMapping(value="/create")
	public ResponseEntity<Object> loadtheData(@RequestBody final OrderService orderService) {
		
		try {
			orderServiceRepository.save(orderService);
			return new ResponseEntity<>(orderService, HttpStatus.OK);
		}catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
		
	}
	
	@GetMapping(value="/{customerName}")
	public ResponseEntity<Object> findByname(@PathVariable final String customerName){
		
		try {
			OrderService orderService = orderServiceRepository.findByCustomerName(customerName);
			if(null==orderService) {
				throw new NotFoundException("Order not found with customerName " + customerName);
			}
			return new ResponseEntity<>(orderService, HttpStatus.OK);
		}catch(NotFoundException e) {
			 throw new NotFoundException("Order not found with customerName " + customerName);
		}
		catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
		
	}
	
	@GetMapping(value="/getOrder/{id}")
	public ResponseEntity<Object> findById(@PathVariable final Integer id){
		try {
			Optional<OrderService> orderService =  orderServiceRepository.findById(id);
			return new ResponseEntity<>(orderService, HttpStatus.OK);
			
		}catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
	}
	
}
