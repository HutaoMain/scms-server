package com.capstone.scms.repository;

import com.capstone.scms.model.Order;
import com.capstone.scms.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    Order findOrderById(Long id);
}
