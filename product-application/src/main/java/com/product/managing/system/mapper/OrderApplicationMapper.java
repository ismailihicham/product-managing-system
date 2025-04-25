package com.product.managing.system.mapper;

import com.product.managing.system.dto.order.CreateOrderCommandDomain;
import com.product.managing.system.entities.Money;
import com.product.managing.system.entities.Order;
import com.product.managing.system.entities.OrderItem;
import com.product.managing.system.entities.Product;
import com.product.managing.system.model.CreateOrderCommand;
import com.product.managing.system.model.OrderItemCreateQuery;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderApplicationMapper {

    public CreateOrderCommandDomain createOrderCommandToDomain(CreateOrderCommand createOrderCommand) {
        return CreateOrderCommandDomain.builder()
                .customerId(createOrderCommand.getCustomerId())
                .price(new Money(createOrderCommand.getPrice()))
                .order(mapToOrderDomain(createOrderCommand))
                .build();
    }

    private Order mapToOrderDomain(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .price(new Money(createOrderCommand.getPrice()))
                .customerId(createOrderCommand.getCustomerId())
                .items(mapToItemsDomain(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> mapToItemsDomain(@NotNull List<OrderItemCreateQuery> items) {
        return items.stream().map(queryItem -> OrderItem.builder()
                .product(new Product(queryItem.getProductId()))
                .subTotal(new Money(queryItem.getSubTotal()))
                .quantity(queryItem.getQuantity())
                .price(new Money(queryItem.getPrice()))
                .subTotal(new Money(queryItem.getSubTotal()))
                .build()
        ).toList();
    }
}
