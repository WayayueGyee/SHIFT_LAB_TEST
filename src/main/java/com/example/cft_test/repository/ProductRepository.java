package com.example.cft_test.repository;

import com.example.cft_test.model.Product;
import com.redis.om.spring.repository.RedisDocumentRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends RedisDocumentRepository<Product, String> {
    Optional<List<Product>> findAllByCategory(String category);
}
