package com.example.cft_test.service.impl;

import com.example.cft_test.exception.AlreadyExistsException;
import com.example.cft_test.exception.NotFoundException;
import com.example.cft_test.model.Product;
import com.example.cft_test.repository.ProductRepository;
import com.example.cft_test.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) throws AlreadyExistsException {
        if (productRepository.findById(product.getId()).isPresent()) {
            throw new AlreadyExistsException(product.getClass(), product.getId());
        }

        return productRepository.save(product);
    }

    @Override
    public boolean update(Product product, String id) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);

            return true;
        }

        return false;
    }

    @Override
    public List<Product> readAll() {
        List<Product> result = new ArrayList<>();

        productRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Product readById(String id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Product> readByCategory(String category) throws NotFoundException {
        return productRepository.findAllByCategory(category).orElseThrow(NotFoundException::new);
    }

    @Override
    public boolean delete(String id) {
        return productRepository
                .findById(id)
                .map(product -> {
                    productRepository.deleteById(id);

                    return true;
                })
                .orElse(false);
    }
}
