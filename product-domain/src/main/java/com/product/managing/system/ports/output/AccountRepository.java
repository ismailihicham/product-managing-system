package com.product.managing.system.ports.output;

import com.product.managing.system.entities.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Optional<Account> findUser(UUID id);
}
