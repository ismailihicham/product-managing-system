package com.product.managing.system.model;

import com.product.managing.system.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ModifyProductQuery {
    @NotNull
    private final Product product;

    public ModifyProductQuery(Product product) {
        this.product = product;
    }
}
