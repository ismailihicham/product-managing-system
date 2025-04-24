package com.product.managing.system.entities;

import com.product.managing.system.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void initializeOrder() {
        this.orderId = UUID.randomUUID();
        status = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        for (OrderItem item : items) {
            if (!item.isPriceValid()) {
                throw new DomainException("Order item price is not valid!");
            }
            item.initializeOrderItem(this.getOrderId());
        }
    }

    public Order addItemToOrder(List<OrderItem> newItems) {
        for (OrderItem item : newItems) {
            item.initializeOrderItem(this.getOrderId());
        }
        List<OrderItem> oldListToUpdate = new ArrayList<>(this.items);
        oldListToUpdate.addAll(newItems);
        this.items = oldListToUpdate;
        var total = newItems.stream()
                .map(item -> item.getSubTotal())
                .reduce(Money.ZERO, Money::add);
        this.price = price.add(total);
        return this;

    }

    public Order removeFromOrder(List<OrderItem> itemsToRemove) {
        List<OrderItem> oldListToUpdate = new ArrayList<>(this.items);
        Iterator<OrderItem> iterator = oldListToUpdate.iterator();
        while (iterator.hasNext()) {
            OrderItem item = iterator.next();

            for (OrderItem i : itemsToRemove) {
                if (item.getProduct().equals(i.getProduct())) {
                    item.setQuantity(item.getQuantity() - i.getQuantity());
                }
            }

            if (item.getQuantity() <= 0) {
                iterator.remove();
            }
        }

        for (OrderItem item : oldListToUpdate) {
            item.initializeOrderItem(this.getOrderId());
        }
        var total = itemsToRemove.stream()
                .map(item -> item.getSubTotal())
                .reduce(Money.ZERO, Money::add);
        this.price = price.subtract(total);
        return this;

    }

}
