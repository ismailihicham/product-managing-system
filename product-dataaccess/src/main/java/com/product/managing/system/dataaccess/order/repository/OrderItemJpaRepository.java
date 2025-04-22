package com.product.managing.system.dataaccess.order.repository;

import com.product.managing.system.dataaccess.order.entities.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemJpaRepository extends JpaRepository<OrderItemEntity, Long> {
}
