package com.product.managing.system.mapper;

import com.product.managing.system.dto.order.CreateOrderCommandDomain;
import com.product.managing.system.entities.Order;
import com.product.managing.system.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public Order orderCommandToOrder(CreateOrderCommandDomain createOrderCommand) {
        return Order.builder()
                .orderId(createOrderCommand.getOrder().getOrderId())
                .customerId(createOrderCommand.getCustomerId())
                .price(createOrderCommand.getOrder().getPrice())
                .items(createOrderCommand.getOrder().getItems())
                .status(OrderStatus.PENDING)
                .build();
    }
}
