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

    public void initializeOrder() {
        this.orderId = UUID.randomUUID();
        status = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        for (OrderItem item : items) {
            item.initializeOrderItem(this.getOrderId());
        }
    }

    public void addItemToOrder(List<OrderItem> newItems) {
        for (OrderItem item : items) {
            item.initializeOrderItem(this.getOrderId());
        }
        this.items.addAll(newItems);
        var total = newItems.stream()
                .map(item -> item.getSubTotal())
                .reduce(Money.ZERO, Money::add);
        this.price = price.add(total);

    }

    public void removeFromOrder(List<OrderItem> itemsToRemove) {
        this.items.removeIf(item ->
                itemsToRemove.stream()
                        .anyMatch(toRemove -> toRemove.equals(item))
        );
        for (OrderItem item : items) {
            item.initializeOrderItem(this.getOrderId());
        }
        var total = itemsToRemove.stream()
                .map(item -> item.getSubTotal())
                .reduce(Money.ZERO, Money::add);
        this.price = price.subtract(total);

    }



}
