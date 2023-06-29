package com.capstone.scms.controller;

import com.capstone.scms.dto.OrderDto;
import com.capstone.scms.model.Order;
import com.capstone.scms.repository.OrderRepository;
import com.capstone.scms.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping("/{email}/create")
    private ResponseEntity<Order> createOrder(@RequestBody OrderDto orderDto, @PathVariable String email) {
        Order order = orderService.createOrder(orderDto, email);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/list")
    public List<Order> getListOrder() {
        return orderService.getListOrder();
    }

    @GetMapping("/list/{orderId}")
    private Order getOrderById(@PathVariable("orderId") long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping(value = "/updateStatus/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody Order order) {
        orderService.updateOrderStatus(id, order);
        return ResponseEntity.ok("successfully upload receipt!");
    }

    @GetMapping("/list-sales")
    private List<OrderRepository.SumOfTotalPriceInterface> getByDayPrice() {
        return orderService.priceByDay();
    }
}
