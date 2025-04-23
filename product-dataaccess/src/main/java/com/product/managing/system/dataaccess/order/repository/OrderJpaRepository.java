package com.product.managing.system.dataaccess.order.repository;

import com.product.managing.system.dataaccess.order.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {

    @Transactional
    default OrderEntity updateOrInsert(OrderEntity entity) {
        return save(entity);
    }
}
