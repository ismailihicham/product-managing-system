package com.product.managing.system.entities;

import com.product.managing.system.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Product {
    private UUID productId;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Money price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    private InventoryStatus inventoryStatus;
    private Long rating;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void initializeProduct() {
        this.productId = UUID.randomUUID();
        this.inventoryStatus = InventoryStatus.OUTOFSTOCK;
        this.createdAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void validateInitializeProduct() {
        if(this.productId != null || this.updatedAt != null) {
            throw new DomainException("Product is not in correct state for initialization! ");
        }
    }

    public Product updateProduct(Product p) {
        return Product.builder()
                .productId(UUID.randomUUID())
                .code(p.getCode())
                .name(p.getName())
                .description(p.getDescription())
                .image(p.getImage())
                .category(p.getCategory())
                .price(p.getPrice())
                .quantity(p.getQuantity())
                .internalReference(p.internalReference)
                .shellId(p.getShellId())
                .inventoryStatus(p.getInventoryStatus())
                .rating(p.getRating())
                .createdAt(p.getCreatedAt())
                .updatedAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }


}
