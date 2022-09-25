package com.example.cft_test.repository;

import com.example.cft_test.model.Characteristics;
import com.redis.om.spring.repository.RedisDocumentRepository;

public interface CharRepository extends RedisDocumentRepository<Characteristics, String> {
}
