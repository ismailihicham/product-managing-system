package com.product.managing.system.model;

import com.product.managing.system.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class ModifyProductQuery {

    @NotNull
    private final UUID userId;

    @NotNull
    private final Product product;

    public ModifyProductQuery(UUID userId, Product product) {
        this.userId = userId;
        this.product = product;
    }
}
