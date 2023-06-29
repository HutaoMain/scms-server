package com.capstone.scms.service;

import com.capstone.scms.dto.OrderDto;
import com.capstone.scms.dto.ProductQuantityDto;
import com.capstone.scms.model.Order;
import com.capstone.scms.model.Product;
import com.capstone.scms.model.User;
import com.capstone.scms.repository.OrderRepository;
import com.capstone.scms.repository.ProductRepository;
import com.capstone.scms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    public Order createOrder(OrderDto orderDto, String email) {
        User user = userRepository.findByEmail(email);
        Order order = new Order();

        if (user == null) {
            log.info("user is not present");
        } else {
            order.setEmail(orderDto.getEmail());
            order.setTotalPrice(orderDto.getTotalPrice());
            order.setQuantity(orderDto.getQuantity());
            order.setProductName(orderDto.getProductName());
            order.setProductDescription(orderDto.getProductDescription());
            order.setProductPrice(orderDto.getProductPrice());
            order.setProductId(orderDto.getProductId());
            order.setUser(user);

            List<ProductQuantityDto> productQuantities = orderDto.getProducts();
            subtractProductsFromInventory(productQuantities);

            orderRepository.save(order);
        }

        return order;
    }

    private void subtractProductsFromInventory(List<ProductQuantityDto> productQuantities) {
        for (ProductQuantityDto pq : productQuantities) {
            Long productId = pq.getProductId();
            Integer quantity = pq.getQuantity();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

            Integer currentQuantity = product.getQuantity();
            if (currentQuantity < quantity) {
                throw new RuntimeException("Insufficient quantity for product with ID: " + productId);
            }

            product.setQuantity(currentQuantity - quantity);
            productRepository.save(product);
        }
    }

   public void updateOrderStatus(Long id, Order order) {
        Order setOrder = orderRepository.findById(id).orElse(null);
        if (setOrder != null) {
            setOrder.setStatus(order.getStatus());
            orderRepository.save(setOrder);
        }
    }
    public List<Order> getListOrder() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public void deleteOrderById(long orderId, Order order) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderRepository.SumOfTotalPriceInterface> priceByDay() {
        return orderRepository.getTotalPriceByDate();
    }
}
