package com.example.cft_test.service;

import com.example.cft_test.exception.AlreadyExistsException;
import com.example.cft_test.exception.NotFoundException;
import com.example.cft_test.model.Product;

import java.util.List;

public interface ProductService {
    Product save(Product object) throws AlreadyExistsException;

    boolean update(Product object, String id);

    List<Product> readAll();

    Product readById(String id) throws NotFoundException;

    List<Product> readByCategory(String category) throws NotFoundException;

    boolean delete(String id);
}
