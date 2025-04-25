package com.product.managing.system.entities;

import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

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

    public Product() {
    }
    public Product(UUID productId) {
        this.productId = productId;
    }

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
        return new Product.Builder()
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

    public static final class Builder {
        private Product product = new Product();

        public Builder() {
        }

        public Builder productId(UUID val) {
            product.productId = val;
            return this;
        }

        public Builder code(String val) {
            product.code = val;
            return this;
        }

        public Builder name(String val) {
            product.name = val;
            return this;
        }

        public Builder description(String val) {
            product.description = val;
            return this;
        }

        public Builder image(String val) {
            product.image = val;
            return this;
        }

        public Builder category(String val) {
            product.category = val;
            return this;
        }

        public Builder price(Money val) {
            product.price = val;
            return this;
        }

        public Builder quantity(int val) {
            product.quantity = val;
            return this;
        }

        public Builder internalReference(String val) {
            product.internalReference = val;
            return this;
        }

        public Builder shellId(Long val) {
            product.shellId = val;
            return this;
        }

        public Builder inventoryStatus(InventoryStatus val) {
            product.inventoryStatus = val;
            return this;
        }

        public Builder rating(Long val) {
            product.rating = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            product.createdAt = val;
            return this;
        }

        public Builder updatedAt(ZonedDateTime val) {
            product.updatedAt = val;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
