package com.product.managing.system.entities;

import com.product.managing.system.dto.product.UpdateProductCommand;
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
        if(this.updatedAt != null) {
            throw new DomainException("Product is not in correct state for initialization! ");
        }
    }

    public Product updateProduct(UpdateProductCommand updateProductCommand) {
        var p = updateProductCommand.getProduct();
        return Product.builder()
                .productId(updateProductCommand.getProductId())
                .code(keepIfNull(p.getCode(), getCode()))
                .name(keepIfNull(p.getName(), getName()))
                .description(keepIfNull(p.getDescription(), getDescription()))
                .image(keepIfNull(p.getImage(), getImage()))
                .category(keepIfNull(p.getCategory(),getCategory()))
                .price((p.getPrice() != null && !p.getPrice().equals(Money.ZERO)) ? p.getPrice(): getPrice())
                .quantity(p.getQuantity() != 0 ? p.getQuantity():getQuantity())
                .internalReference(keepIfNull(p.getInternalReference(), getInternalReference()))
                .shellId((p.getShellId() != null && p.getShellId() != 0)? p.getShellId(): getShellId())
                .inventoryStatus(keepIfNull(p.getInventoryStatus(), getInventoryStatus()))
                .rating((p.getRating() != null && p.getRating() != 0) ? p.getRating(): getRating())
                .createdAt(getCreatedAt())
                .updatedAt(ZonedDateTime.now(ZoneId.of("UTC")))
                .build();
    }

    public static <T> T keepIfNull(T newValue, T currentValue) {
        return newValue != null ? newValue : currentValue;
    }



}
