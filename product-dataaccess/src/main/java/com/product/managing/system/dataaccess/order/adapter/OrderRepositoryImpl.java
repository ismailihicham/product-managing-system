package com.product.managing.system.dataaccess.order.adapter;

import com.product.managing.system.entities.Order;
import com.product.managing.system.ports.output.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public Order saveOrder(Order order) {
        return null;
    }

    @Override
    public Order modifyOrder(UUID orderId, Order order) {
        return null;
    }

    @Override
    public void cancelOrder(UUID orderId) {

    }
}
