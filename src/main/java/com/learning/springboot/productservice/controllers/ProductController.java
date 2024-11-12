package com.learning.springboot.productservice.controllers;

import com.learning.springboot.productservice.DTOs.CreateProductRequestDTO;
import com.learning.springboot.productservice.DTOs.ErrorDTO;
import com.learning.springboot.productservice.Exceptions.ProductNotFoundException;
import com.learning.springboot.productservice.models.Product;
import com.learning.springboot.productservice.services.FakeStoreProductService;
import com.learning.springboot.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {
    ProductService productService = new FakeStoreProductService(new RestTemplate());

    //Get all products in a specific category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductsInSpecificCategory(@PathVariable("category") String category){
        return productService.getProductsInSpecificCategory(category);
    }

    //Add a product
    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO request){
        return productService.createProduct(request.getTitle(), request.getPrice(), request.getImage(), request.getDescription(), request.getCategory());
    }

    //Get details of a single product
    @GetMapping("/product/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId) throws ProductNotFoundException {
            return productService.getSingleProduct(productId);
    }

    //Delete a product
    @DeleteMapping("/product/{productID}")
    public void deleteProduct(@PathVariable("productID") Long productID){
        productService.deleteProduct(productID);
    }

    //Get all categories
    @GetMapping("/products/categories")
    public List<String> getAllCategories(){
        return productService.getAllCategories();
    }

    //Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
