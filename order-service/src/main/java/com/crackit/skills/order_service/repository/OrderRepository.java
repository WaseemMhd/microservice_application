package com.crackit.skills.order_service.repository;

import com.crackit.skills.order_service.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
