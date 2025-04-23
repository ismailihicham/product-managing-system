package com.product.managing.system.dto.product;

import com.product.managing.system.entities.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class CreateProductCommand {
    @NotNull
    private final Product product;

    public CreateProductCommand(Product product) {
        this.product = product;
    }
}
