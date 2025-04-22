package com.product.managing.system.dataaccess.product.mapper;

import com.product.managing.system.entities.Money;
import com.product.managing.system.entities.Product;
import com.product.managing.system.dataaccess.product.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDataAccessMapper {

    public ProductEntity productToProductEntity(Product product) {
        return ProductEntity.builder()
                .productId(product.getProductId())
                .code(product.getCode())
                .category(product.getCategory())
                .image(product.getImage())
                .price(product.getPrice().getAmount())
                .description(product.getDescription())
                .quantity(product.getQuantity())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .internalReference(product.getInternalReference())
                .shellId(product.getShellId())
                .inventoryStatus(product.getInventoryStatus())
                .build();
    }

    public Product productEntityToProduct(ProductEntity productEntity) {
        return Product.builder()
                .productId(productEntity.getProductId())
                .code(productEntity.getCode())
                .category(productEntity.getCategory())
                .image(productEntity.getImage())
                .price(new Money(productEntity.getPrice()))
                .description(productEntity.getDescription())
                .quantity(productEntity.getQuantity())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .internalReference(productEntity.getInternalReference())
                .shellId(productEntity.getShellId())
                .inventoryStatus(productEntity.getInventoryStatus())
                .build();
    }
}
