package com.product.managing.system.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderItem {
    private UUID orderId;
    private UUID orderItemId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;

    public boolean isPriceValid() {
        return price.isGreaterThanZero()
                && price.multiply(quantity).equal(subTotal);
    }

    public void initializeOrderItem(UUID orderId) {
        this.orderId = orderId;
        this.orderItemId = UUID.randomUUID();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(orderId, orderItem.orderId) && Objects.equals(product, orderItem.product) && Objects.equals(price, orderItem.price) && Objects.equals(subTotal, orderItem.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, product, quantity, price, subTotal);
    }
}
