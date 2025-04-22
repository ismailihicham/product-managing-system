package com.product.managing.system.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderItem {
    private UUID orderItemId;
    private UUID orderId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;

    public boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.equals(product.getPrice())
                && price.multiply(quantity).equals(subTotal);
    }

    public void initializeOrderItem(UUID orderId, UUID orderItemId) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
    }
}
