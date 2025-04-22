package com.product.managing.system.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Order {
    private UUID orderId;
    private UUID customerId;
    private Money price;
    private List<OrderItem> items;
    private OrderStatus status;
    private List<String> failuresMessages;
}
