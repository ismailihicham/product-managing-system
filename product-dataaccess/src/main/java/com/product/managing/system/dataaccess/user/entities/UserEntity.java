package com.product.managing.system.dataaccess.user.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user_account")
public class UserEntity {
    @Id
    @Column(name = "user_id", columnDefinition = "UUID")
    private UUID userId;
    private String userName;
    private String firstName;
    private String email;
    private String password;
}
