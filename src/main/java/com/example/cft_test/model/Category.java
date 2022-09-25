package com.example.cft_test.model;

import com.redis.om.spring.annotations.Document;
import com.redis.om.spring.annotations.Indexed;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Document
public class Category {
    @Id
    private String category;
    @Indexed
    private Set<String> products;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public Category(String category, Set<String> products) {
        this.category = category;
        this.products = products;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<String> getProducts() {
        return products;
    }

    public void setProducts(Set<String> products) {
        this.products = products;
    }
}
