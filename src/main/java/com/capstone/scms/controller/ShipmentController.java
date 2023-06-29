package com.capstone.scms.controller;

import com.capstone.scms.dto.ShipmentDto;
import com.capstone.scms.dto.ShipmentWithOrderDto;
import com.capstone.scms.model.Shipment;
import com.capstone.scms.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
@CrossOrigin("*")
public class ShipmentController {

    @Autowired
    ShipmentService shipmentService;

    @PostMapping("/create")
    public ResponseEntity<Shipment> createShipment(@RequestBody ShipmentDto shipmentDto) {
        Shipment createdShipment = shipmentService.createShipment(shipmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShipment);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ShipmentWithOrderDto>> getShipmentList() {
        List<ShipmentWithOrderDto> shipments = shipmentService.getShipmentList();
        return ResponseEntity.ok(shipments);
    }

    // Get shipment by ID
    @GetMapping("/list/{id}")
    public ResponseEntity<ShipmentWithOrderDto> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        if (shipment == null) {
            return ResponseEntity.notFound().build();
        }
        ShipmentWithOrderDto shipmentDto = shipmentService.convertToDto(shipment);
        return ResponseEntity.ok(shipmentDto);
    }

    // Update shipment
    @PutMapping("/update/{id}")
    private ResponseEntity<String> updateShipment(@PathVariable Long id, @RequestBody ShipmentDto shipmentDto) {
        shipmentService.updateShipment(id, shipmentDto);
        return ResponseEntity.ok("updated");
    }

    // Delete shipment
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        boolean deleted = shipmentService.deleteShipment(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
