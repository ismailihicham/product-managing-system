package com.product.managing.system.dataaccess.user.repository;

import com.product.managing.system.dataaccess.user.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
