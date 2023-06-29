package com.capstone.scms.dto;

import lombok.Data;

@Data
public class ShipmentWithOrderDto {

    private Long shipmentId;
    private String email;
    private String shipmentStatus;
    private Long orderId;
    private Double orderTotalPrice;
    private String orderProductName;
    private String orderProductDescription;
}
