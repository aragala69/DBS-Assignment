package com.order.item.service;

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
@RequestMapping("/orderitem")
public class OrderItemController {
	
	@Autowired
	private OrderItemServiceRepository orderItemServiceRepository;
	
	@GetMapping(value="/get")
	public ResponseEntity<Object> findAll(){
		
		try {
			 List<OrderItem> orderServiceList =  orderItemServiceRepository.findAll();
			 return new ResponseEntity<>(orderServiceList, HttpStatus.OK);
		 }catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		 }
	}
	
	@PostMapping(value="/create")
	public ResponseEntity<Object> createOrderItem(@RequestBody final OrderItem orderItem) {
		
		try {
			orderItemServiceRepository.save(orderItem);
			return new ResponseEntity<>(orderItem, HttpStatus.OK);
		}catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
	}
	
	@GetMapping(value="/{productCode}")
	public ResponseEntity<Object> findByname(@PathVariable final String productCode){
		
		try {
			OrderItem orderItem = orderItemServiceRepository.findByProductCode(productCode);
			if(null==orderItem) {
				throw new NotFoundException("Order not found with customerName " + productCode);
			}
			return new ResponseEntity<>(orderItem, HttpStatus.OK);
		}catch(NotFoundException e) {
			 throw new NotFoundException("Order not found with customerName " + productCode);
		}
		catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
		
	}
	
	@GetMapping(value="/getItem/{id}")
	public ResponseEntity<Object> findById(@PathVariable final Integer id){
		try {
			Optional<OrderItem> orderItem =  orderItemServiceRepository.findById(id);
			return new ResponseEntity<>(orderItem, HttpStatus.OK);
			
		}catch(Exception e) {
			 throw new NotFoundException("Internal Error");
		}
	}

}
