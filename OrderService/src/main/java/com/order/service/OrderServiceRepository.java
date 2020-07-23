package com.order.service;

import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderServiceRepository extends JpaRepository<OrderService, Integer> {

	OrderService findByCustomerName(String name);

}
