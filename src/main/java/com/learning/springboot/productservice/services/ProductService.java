package com.learning.springboot.productservice.services;

import com.learning.springboot.productservice.Exceptions.ProductNotFoundException;
import com.learning.springboot.productservice.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productID) throws ProductNotFoundException;

    List<Product> getProducts();

    List<Product> getProductsInSpecificCategory(String category);
    Product createProduct(String title, double price, String image, String description, String category);
    void deleteProduct(Long productID);

    List<String> getAllCategories();

    List<Product> getAllProducts();
}
