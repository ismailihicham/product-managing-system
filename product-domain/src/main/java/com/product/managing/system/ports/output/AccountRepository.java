package com.product.managing.system.ports.output;

import com.product.managing.system.entities.Account;

import java.util.UUID;

public interface AccountRepository {

    Account findAccount(UUID id);

    Account createAccount(Account account);

}
