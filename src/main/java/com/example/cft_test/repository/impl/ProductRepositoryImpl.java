package com.example.cft_test.repository.impl;

import com.example.cft_test.model.Product;
import com.example.cft_test.repository.ProductRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    public static final String HASH_KEY = "Public";
    private RedisTemplate<String, Product> template;

    @Override
    public Product save(Product product) {
        template.opsForHash().put(HASH_KEY, product.getId(), product);

        return product;
    }

    @Override
    public List<Product> readAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    @Override
    public Product readById(Long id) {
        return null;
    }

    @Override
    public boolean update(Product product, Long id) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
*/
