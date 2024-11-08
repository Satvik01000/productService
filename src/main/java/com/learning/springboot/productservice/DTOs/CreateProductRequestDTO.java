package com.learning.springboot.productservice.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;
}
