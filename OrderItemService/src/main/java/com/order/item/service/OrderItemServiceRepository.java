package com.order.item.service;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemServiceRepository extends JpaRepository<OrderItem, Integer> {

	OrderItem findByProductCode(String name);


}
