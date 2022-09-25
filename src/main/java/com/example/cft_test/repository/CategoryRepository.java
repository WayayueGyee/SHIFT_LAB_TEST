package com.example.cft_test.repository;

import com.example.cft_test.model.Category;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface CategoryRepository extends RedisDocumentRepository<Category, String> {
}
