package com.learning.springboot.productservice.DTOs;

import com.learning.springboot.productservice.models.Category;
import com.learning.springboot.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public Product toProduct(){
        Product product=new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category productCategory = new Category();
        productCategory.setTitle(category);

        product.setCategory(productCategory);

        return product;
    }
}
