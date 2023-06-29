package com.capstone.scms.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private String email;
    private Double totalPrice;
    private Integer quantity;

    private Long productId;
    private String productName;
    private String productDescription;
    private Double productPrice;

    private List<ProductQuantityDto> products;
}
