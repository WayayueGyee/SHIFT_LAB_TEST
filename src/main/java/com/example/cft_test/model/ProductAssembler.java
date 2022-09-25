package com.example.cft_test.model;

import com.example.cft_test.controller.ProductController;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public @NotNull EntityModel<Product> toModel(@NotNull Product product) {
        return EntityModel.of(
                product,
                linkTo(methodOn(ProductController.class).readById(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).readAll()).withRel("products")
        );
    }

    @Override
    public @NotNull CollectionModel<EntityModel<Product>> toCollectionModel(@NotNull Iterable<? extends Product> products) {
        List<EntityModel<Product>> emProducts = new ArrayList<>();

        for (Product product : products) {
            emProducts.add(toModel(product));
        }

        return CollectionModel.of(
                emProducts,
                linkTo(methodOn(ProductController.class).readAll()).withSelfRel()
        );
    }
}
