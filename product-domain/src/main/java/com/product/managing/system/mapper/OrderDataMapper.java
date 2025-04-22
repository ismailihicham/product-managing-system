package com.product.managing.system.mapper;

import com.product.managing.system.dto.order.OrderCommand;
import com.product.managing.system.dto.order.OrderCommandResponse;
import com.product.managing.system.entities.Order;
import com.product.managing.system.entities.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    public Order orderCommandToOrder(OrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(createOrderCommand.getCustomerId())
                .price(createOrderCommand.getOrder().getPrice())
                .items(createOrderCommand.getOrder().getItems())
                .status(OrderStatus.PENDING)
                .build();
    }

    public OrderCommandResponse orderToOrderCommandResponse(Order order) {
        return OrderCommandResponse.builder()
                .orderId(order.getOrderId())
                .status(order.getStatus())
                .message("ORDER APPROVE AND SAVED")
                .build();
    }
}
