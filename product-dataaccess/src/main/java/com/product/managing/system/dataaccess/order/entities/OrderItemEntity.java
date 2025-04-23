package com.product.managing.system.dataaccess.order.entities;

import com.product.managing.system.dataaccess.product.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "order_items")
public class OrderItemEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long orderItemId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "product_id")
    @OneToOne
    private ProductEntity product;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subTotal;
}
