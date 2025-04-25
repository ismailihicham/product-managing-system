package com.product.managing.system.model;

import com.product.managing.system.entities.Money;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderCommand {

    @NotNull
    private final UUID customerId;
    @NotNull
    private BigDecimal price;
    @NotNull
    private List<OrderItemCreateQuery> items;
}
