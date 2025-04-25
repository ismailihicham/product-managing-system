package com.product.managing.system.model;

import com.product.managing.system.entities.Money;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderItemCreateQuery {
    @NotNull
    private UUID productId;
    @NotNull
    private int quantity;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal subTotal;
}
