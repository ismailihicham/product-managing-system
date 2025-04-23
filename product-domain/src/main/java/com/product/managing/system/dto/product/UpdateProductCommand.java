package com.product.managing.system.dto.product;

import com.product.managing.system.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UpdateProductCommand {

    @NotNull
    private final UUID productId;
    @NotNull
    private final Product product;

    public UpdateProductCommand(UUID productId, Product product) {
        this.productId = productId;
        this.product = product;
    }
}
