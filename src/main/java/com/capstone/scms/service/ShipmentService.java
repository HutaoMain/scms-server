package com.capstone.scms.service;

import com.capstone.scms.dto.ShipmentDto;
import com.capstone.scms.dto.ShipmentWithOrderDto;
import com.capstone.scms.model.Order;
import com.capstone.scms.model.Shipment;
import com.capstone.scms.repository.OrderRepository;
import com.capstone.scms.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    OrderRepository orderRepository;

    public Shipment createShipment(ShipmentDto shipmentDto) {
        Order order = orderRepository.findById(shipmentDto.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + shipmentDto.getOrderId()));

        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setStatus(shipmentDto.getStatus());

        return shipmentRepository.save(shipment);
    }

    public Shipment getShipmentById(Long id) {
        return shipmentRepository.findById(id).orElse(null);
    }

    public List<ShipmentWithOrderDto> getShipmentList() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return convertToDtoList(shipments);
    }

    private List<ShipmentWithOrderDto> convertToDtoList(List<Shipment> shipments) {
        List<ShipmentWithOrderDto> dtos = new ArrayList<>();
        for (Shipment shipment : shipments) {
            dtos.add(convertToDto(shipment));
        }
        return dtos;
    }

    public ShipmentWithOrderDto convertToDto(Shipment shipment) {
        ShipmentWithOrderDto dto = new ShipmentWithOrderDto();
        dto.setShipmentId(shipment.getId());
        dto.setEmail(shipment.getOrder().getEmail());
        dto.setShipmentStatus(shipment.getStatus());
        dto.setOrderId(shipment.getOrder().getId());
        dto.setOrderTotalPrice(shipment.getOrder().getTotalPrice());
        dto.setOrderProductName(shipment.getOrder().getProductName());
        dto.setOrderProductDescription(shipment.getOrder().getProductDescription());

        return dto;
    }


    public void updateShipment(Long id, ShipmentDto shipmentDto) {
        Shipment existingShipment = shipmentRepository.findById(id).orElse(null);
        if (existingShipment != null) {
            Order order = orderRepository.findById(shipmentDto.getOrderId()).orElse(null);
            existingShipment.setOrder(order);
            existingShipment.setStatus(shipmentDto.getStatus());
            // Update other attributes if necessary
             shipmentRepository.save(existingShipment);
        }

    }


    public boolean deleteShipment(Long id) {
        if (shipmentRepository.existsById(id)) {
            shipmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
