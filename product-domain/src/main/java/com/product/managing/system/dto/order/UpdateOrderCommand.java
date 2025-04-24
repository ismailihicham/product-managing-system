package com.product.managing.system.dto.order;

import com.product.managing.system.entities.Order;
import com.product.managing.system.entities.OrderItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UpdateOrderCommand {
    private List<OrderItem> items;
    private Order order;
    private UUID customerId;

}
