package com.product.managing.system.ports.output;

import com.product.managing.system.entities.Order;

import java.util.UUID;

public interface OrderRepository {

    Order saveOrder(Order order);

    Order modifyOrder(UUID orderId, Order order);

    void cancelOrder(UUID orderId);

}
