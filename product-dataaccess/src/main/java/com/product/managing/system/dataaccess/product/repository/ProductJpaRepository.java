package com.product.managing.system.dataaccess.product.repository;

import com.product.managing.system.dataaccess.product.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    void deleteById(UUID productId);

    @Transactional
    default ProductEntity updateOrInsert(ProductEntity entity) {
        return save(entity);
    }
}
