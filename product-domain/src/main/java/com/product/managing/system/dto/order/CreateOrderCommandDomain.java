package com.product.managing.system.dto.order;

import com.product.managing.system.entities.Money;
import com.product.managing.system.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CreateOrderCommandDomain {

    private final UUID customerId;
    private Money price;
    private Order order;
}
