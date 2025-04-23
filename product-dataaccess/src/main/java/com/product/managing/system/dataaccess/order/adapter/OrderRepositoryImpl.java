package com.product.managing.system.dataaccess.order.adapter;

import com.product.managing.system.dataaccess.order.mapper.OrderDataAccessMapper;
import com.product.managing.system.dataaccess.order.repository.OrderJpaRepository;
import com.product.managing.system.entities.Order;
import com.product.managing.system.ports.output.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepository;
    private final OrderDataAccessMapper mapper;

    public OrderRepositoryImpl(OrderJpaRepository jpaRepository, OrderDataAccessMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Order saveOrder(Order order) {
        var orderEntity = jpaRepository.save(mapper.orderToOrderEntity(order));
        return mapper.orderEntityToOrder(orderEntity);
    }

    @Override
    public Order modifyOrder(Order order) {
        var orderEntity = jpaRepository.updateOrInsert(mapper.orderToOrderEntity(order));
        return mapper.orderEntityToOrder(orderEntity);
    }

    @Override
    public void cancelOrder(UUID orderId) {
        jpaRepository.deleteById(orderId);
    }
}
