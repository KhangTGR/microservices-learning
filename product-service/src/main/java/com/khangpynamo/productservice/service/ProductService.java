package com.khangpynamo.productservice.service;

import com.khangpynamo.productservice.dto.ProductRequest;
import com.khangpynamo.productservice.dto.ProductResponse;
import com.khangpynamo.productservice.model.Product;
import com.khangpynamo.productservice.repository.ProductRepository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    
    private final ProductRepository productRepository;
    
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
                
        try {
            productRepository.save(product);
            log.info("Successfully saved product with ID: {}", product.getId());
        } catch (Exception e) {
            log.error("Failed to save product with name '{}' due to: {}", productRequest.getName(), e.getMessage());
        }
        
        log.info("Created product with ID '{}' and name: {}", product.getId(), product.getName());
    }
    
    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
    
    public List<ProductResponse> getAllProducts(){
        try {
            List<Product> products = productRepository.findAll();
            log.info("Retrieved {} products from the database", products.size());
            return products.stream().map(this::mapToProductResponse).toList();
        } catch (Exception e) {
            log.error("Failed to retrieve products from the database due to: {}", e.getMessage());
            return List.of();
        }
    }
}