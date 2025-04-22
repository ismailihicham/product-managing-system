package com.product.managing.system.dataaccess.user.adapter;

import com.product.managing.system.entities.Account;
import com.product.managing.system.ports.output.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRespositoryImpl implements AccountRepository {
    @Override
    public Optional<Account> findUser(UUID id) {
        return Optional.empty();
    }
}
