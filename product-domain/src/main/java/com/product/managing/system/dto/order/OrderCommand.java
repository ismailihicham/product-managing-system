package com.product.managing.system.dto.order;

import com.product.managing.system.entities.Order;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class OrderCommand {

    @NotNull
    private final UUID customerId;
    @NotNull
    private final Order order;
}
