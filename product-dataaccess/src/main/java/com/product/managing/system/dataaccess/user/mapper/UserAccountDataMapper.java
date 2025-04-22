package com.product.managing.system.dataaccess.user.mapper;

import com.product.managing.system.dataaccess.user.entities.UserEntity;
import com.product.managing.system.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class UserAccountDataMapper {

    public UserEntity accountToUserEntity(Account account) {
        return UserEntity.builder()
                .userId(account.getAccountId())
                .userName(generateUsername(account.getFirstName(), account.getLastName()))
                .firstName(account.getFirstName())
                .password(account.getPassword())
                .email(account.getEmail())
                .build();
    }

    private String generateUsername(String firstName, String lastName) {
        return (firstName + "." + lastName).toLowerCase().replaceAll("\\s+", "");
    }

    public Account userToAccount(UserEntity user) {
        return Account.builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .accountId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
