package com.example.cft_test.service.impl;

import com.example.cft_test.exception.AlreadyExistsException;
import com.example.cft_test.exception.NotFoundException;
import com.example.cft_test.model.Category;
import com.example.cft_test.model.Product;
import com.example.cft_test.repository.CategoryRepository;
import com.example.cft_test.repository.ProductRepository;
import com.example.cft_test.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private void addToCategory(Product newProduct) {
        categoryRepository.save(
                categoryRepository
                        .findById(newProduct.getCategory())
                        .map(category -> {
                            category.getProducts().add(newProduct.getId());
                            category.setProducts(category.getProducts());
                            return category;
                        })
                        .orElseGet(() -> {
                            Set<String> s = new HashSet<>();
                            s.add(newProduct.getId());
                            return new Category(newProduct.getCategory(), s);
                        })
        );
    }

    private void removeFromCategory(Product oldProduct) {
        categoryRepository
                .findById(oldProduct.getCategory())
                .map(category -> {
                    Set<String> s = category.getProducts();
                    s.remove(oldProduct.getId());

                    category.setProducts(s);

                    return new Category(oldProduct.getCategory(), s);
                }).ifPresent(categoryRepository::save);
    }

    @Override
    public Product save(Product product) throws AlreadyExistsException {
        if (productRepository.findById(product.getId()).isPresent()) {
            throw new AlreadyExistsException(product.getClass(), product.getId());
        }

        addToCategory(product);

        return productRepository.save(product);
    }

    @Override
    public boolean update(Product newProduct, String id) {
        return productRepository
                .findById(id)
                .map(oldProduct -> {
                    newProduct.setId(id);
                    removeFromCategory(oldProduct);
                    addToCategory(newProduct);
                    productRepository.save(newProduct);

                    return true;
                })
                .orElse(false);
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
        List<Product> products = new ArrayList<>();

        for (Product product :
                productRepository
                        .findAllById(
                                categoryRepository
                                        .findById(category)
                                        .map(Category::getProducts)
                                        .orElseThrow(NotFoundException::new)
                        )
        ) {
            products.add(product);
        }

        return products;
    }

    @Override
    public boolean delete(String id) {
        return productRepository
                .findById(id)
                .map(product -> {
                    productRepository.deleteById(id);
                    removeFromCategory(product);

                    return true;
                })
                .orElse(false);
    }
}
