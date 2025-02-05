package com.khangpynamo.productservice.repository;

import com.khangpynamo.productservice.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}