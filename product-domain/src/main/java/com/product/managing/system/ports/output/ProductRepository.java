package com.product.managing.system.ports.output;

import com.product.managing.system.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product saveProduct(Product product);
    Product modifyProduct(Product product);

    Optional<Product> findById(UUID productId);

    void removeProductById(UUID productId);

    List<Product> retrieveAllProducts();
}
