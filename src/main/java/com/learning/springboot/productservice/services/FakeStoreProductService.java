package com.learning.springboot.productservice.services;

import com.learning.springboot.productservice.DTOs.FakeStoreProductDTO;
import com.learning.springboot.productservice.Exceptions.ProductNotFoundException;
import com.learning.springboot.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;


    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(Long productID) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + productID, FakeStoreProductDTO.class);
        if(fakeStoreProductResponse.getStatusCode()!= HttpStatusCode.valueOf(200)){
            //do something
        }
//        fakeStoreProductResponse.getHeaders();
        FakeStoreProductDTO fakeStoreProduct = fakeStoreProductResponse.getBody();
        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException("Product with id : " + String.valueOf(productID) + " not found, search another product");
        }
        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getProductsInSpecificCategory(String category) {
        String url = "https://fakestoreapi.com/products/category/" + category;
        FakeStoreProductDTO[] fakeStoreProductsArray = restTemplate.getForObject(url, FakeStoreProductDTO[].class);

        List<Product> products = new ArrayList<>();
        if (fakeStoreProductsArray != null) {
            for (FakeStoreProductDTO fakeStoreProductDTO : fakeStoreProductsArray) {
                products.add(fakeStoreProductDTO.toProduct());
            }
        }
        return products;
    }


    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(String title, double price, String image, String description, String category) {
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setCategory(category);
        fakeStoreProductDTO.setDescription(description);
        fakeStoreProductDTO.setTitle(title);
        fakeStoreProductDTO.setImage(image);
        fakeStoreProductDTO.setCategory(category);

        FakeStoreProductDTO response =restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProductDTO, FakeStoreProductDTO.class);

        assert response != null;
        return response.toProduct();
    }

    @Override
    public void deleteProduct(Long productID) {
        restTemplate.delete("https://fakestoreapi.com/products/"+productID);
    }

    @Override
    public List<String> getAllCategories() {
        String[] categoriesArray = restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);
        List<String> allCategories = new ArrayList<>();

        if (categoriesArray != null) {
            Collections.addAll(allCategories, categoriesArray);
        }
        return allCategories;
    }

    public List<Product> getAllProducts(){
        FakeStoreProductDTO[] fakeStoreProductDTOArray = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> allProducts = new ArrayList<>();

        assert fakeStoreProductDTOArray != null;
        for(FakeStoreProductDTO i : fakeStoreProductDTOArray)
            allProducts.add(i.toProduct());
        return allProducts;
    }
}
