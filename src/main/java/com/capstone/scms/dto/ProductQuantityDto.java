package com.capstone.scms.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductQuantityDto {

    private Long productId;
    private Integer quantity;
}
