package com.product.managing.system.dataaccess.order.repository;

import com.product.managing.system.dataaccess.order.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, UUID> {
}
