package com.product.managing.system.dataaccess.order.mapper;

import com.product.managing.system.dataaccess.order.entities.OrderEntity;
import com.product.managing.system.dataaccess.order.entities.OrderItemEntity;
import com.product.managing.system.dataaccess.product.mapper.ProductDataAccessMapper;
import com.product.managing.system.entities.Money;
import com.product.managing.system.entities.Order;
import com.product.managing.system.entities.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderDataAccessMapper {

    private final ProductDataAccessMapper productMapper;

    public OrderDataAccessMapper(ProductDataAccessMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderEntity orderToOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getOrderId())
                .customerId(order.getCustomerId())
                .price(order.getPrice().getAmount())
                .items(order.getItems().stream().map(this::orderItemToOrderItemEntity).toList())
                .build();

    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(orderEntity.getId())
                .customerId(orderEntity.getCustomerId())
                .price(new Money(orderEntity.getPrice()))
                .items(orderEntity.getItems().stream().map(this::orderItemEntityToOrderItem).toList())
                .build();
    }

    private OrderItem orderItemEntityToOrderItem(OrderItemEntity orderItemEntity) {
        return OrderItem.builder()
                .orderItemId(orderItemEntity.getOrderItemId())
                .orderId(orderItemEntity.getOrder().getId())
                .price(new Money(orderItemEntity.getPrice()))
                .subTotal(new Money(orderItemEntity.getSubTotal()))
                .quantity(orderItemEntity.getQuantity())
                .build();
    }

    public OrderItemEntity orderItemToOrderItemEntity(OrderItem item) {
        return OrderItemEntity.builder()
                .orderItemId(item.getOrderItemId())
                .product(productMapper.productToProductEntity(item.getProduct()))
                .order(OrderEntity.builder().id(item.getOrderId()).build())
                .quantity(item.getQuantity())
                .subTotal(item.getSubTotal().getAmount())
                .build();

    }
}
