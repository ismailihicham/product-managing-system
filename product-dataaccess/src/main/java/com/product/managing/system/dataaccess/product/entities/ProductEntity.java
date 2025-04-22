package com.product.managing.system.dataaccess.product.entities;

import com.product.managing.system.dataaccess.order.entities.OrderItemEntity;
import com.product.managing.system.entities.InventoryStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "product_id", columnDefinition = "UUID")
    private UUID productId;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private OrderItemEntity orderItem;

    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private BigDecimal price;
    private int quantity;
    private String internalReference;
    private Long shellId;
    @Enumerated(EnumType.STRING)
    private InventoryStatus inventoryStatus;
    private Long rating;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
