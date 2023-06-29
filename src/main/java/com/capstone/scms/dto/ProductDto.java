package com.capstone.scms.dto;

import com.capstone.scms.model.Category;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Integer quantity;

    Category category;

}
