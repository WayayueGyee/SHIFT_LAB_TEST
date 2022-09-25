package com.example.cft_test.controller;

import com.example.cft_test.exception.AlreadyExistsException;
import com.example.cft_test.exception.NotFoundException;
import com.example.cft_test.model.Product;
import com.example.cft_test.model.ProductAssembler;
import com.example.cft_test.service.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private final ProductService productService;
    private final ProductAssembler productAssembler;

    public ProductController(ProductService productService, ProductAssembler productAssembler) {
        this.productService = productService;
        this.productAssembler = productAssembler;
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

    @PostMapping("/products/")
    public ResponseEntity<?> save(@RequestBody Product productInfo) {
        try {
            productInfo.setCategory(productInfo.getCategory());
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
    }

    @GetMapping("/products/")
    public CollectionModel<?> readAll() {
        return productAssembler.toCollectionModel(productService.readAll());
    }

    @GetMapping("/products/{id}")
    public EntityModel<?> readById(@PathVariable String id) {
        try {
            return productAssembler.toModel(productService.readById(id));
        } catch (NotFoundException e) {
            return EntityModel.of(e.getMessage());
        }
    }

    @GetMapping("/{category}")
    public ResponseEntity<?> readAllByCategory(@PathVariable String category) {
        try {
            return ResponseEntity
                    .ok()
                    .body(
                            productAssembler
                                    .toCollectionModel(
                                            productService.readByCategory(category.toLowerCase())
                                    )
                    );
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product newProduct, @PathVariable String id) {
        if (productService.update(newProduct, id)) {
            newProduct.setCategory(newProduct.getCategory());
            newProduct.setId(id);
            EntityModel<Product> em = productAssembler.toModel(newProduct);

            return ResponseEntity.ok(em.getRequiredLink(IanaLinkRelations.SELF).toUri());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (productService.delete(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
