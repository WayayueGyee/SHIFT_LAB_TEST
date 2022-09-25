package com.example.cft_test.controller;

import com.example.cft_test.exception.AlreadyExistsException;
import com.example.cft_test.model.Characteristics;
import com.example.cft_test.model.Product;
import com.example.cft_test.service.ProductService;
import com.example.cft_test.util.Pair;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

/*    // TODO: rewrite using HashMap or sth like that
    private Pair<Product, Characteristics> parseProductInfo(JsonNode info) {
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Product product = om.convertValue(info, new TypeReference<>() {
        });

        String[] fieldNames = new String[]{"characteristics", "chars"};

        for (String fieldName : fieldNames) {
            if (info.has(fieldName)) {
                Characteristics chars = new Characteristics(om.convertValue(info.get(fieldName), new TypeReference<>() {
                }));

                return new Pair<>(product, chars);
            }
        }

        return new Pair<>(product, null);
    }*/

    @PostMapping("/{productCategory}")
    public ResponseEntity<?> save(@PathVariable String productCategory, @RequestBody Product productInfo) {
//        Pair<Product, Characteristics> productInfo = parseProductInfo(info);
//        productInfo.a().setCategory(productCategory);

        try {
            return ResponseEntity.ok(productService.save(productInfo));
        } catch (AlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(
                            "Product with id: " +
                                    productInfo.getId() +
                                    " is already presented"
                    );
        }

//        return ResponseEntity.ok().body(productInfo.toString());
    }

    @GetMapping("/")
    public CollectionModel<?> getAll() {
        return CollectionModel.of(productService.readAll());
    }
}
