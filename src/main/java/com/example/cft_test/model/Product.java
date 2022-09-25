package com.example.cft_test.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.redis.om.spring.annotations.Document;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Product struct
/*
{
    id: int,
    serialNumber: String,
    manufacturer: String,
    category: String,
}
*/

@RedisHash("Product")
@Document
public class Product implements Serializable {
    @Id
    private String id;
    @Indexed
    private String serialNumber;
    @Indexed
    @NonNull
    private String manufacturer;
    @NonNull
    private BigDecimal price;
    @NonNull
    private int quantity;
    @Indexed
    private String sku;
    @Indexed
    private String category;
//    @Indexed
//    private Map<String, String> chars;

    public Product() {
    }

    public Product(@NotNull String manufacturer, @NotNull BigDecimal price, int quantity) {
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public @NotNull String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotNull String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public @NotNull BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

//    public Map<String, String> getChars() {
//        return chars;
//    }
//
//    public void setChars(Map<String, String> chars) {
//        this.chars = chars;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sku='" + sku + '\'' +
                ", category='" + category + '\'' +
//                ", chars='" + chars + '\'' +
                '}';
    }
}
