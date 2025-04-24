package com.product.managing.system.mapper;

import com.product.managing.system.dto.account.AccountCommandResponse;
import com.product.managing.system.dto.account.CreateAccountCommand;
import com.product.managing.system.dto.account.GetAccountResponse;
import com.product.managing.system.entities.Account;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountDataMapper {

    private final PasswordService passwordService;
    public static final String ACCOUNT_CREATED_SUCCESSFUL = "Account created successfully";

    public AccountDataMapper(PasswordService passwordService) {
        this.passwordService = passwordService;
    }


    public Account createAccountCommandToAccount(CreateAccountCommand createAccountCommand) {
        return Account.builder()
                .accountId(UUID.randomUUID())
                .firstName(createAccountCommand.getFirstName())
                .lastName(createAccountCommand.getLastName())
                .email(createAccountCommand.getEmail())
                .password(passwordService.hashPassword(createAccountCommand.getPassword()))
                .userName(generateUsername(createAccountCommand.getFirstName(), createAccountCommand.getLastName()))
                .build();
    }

    public AccountCommandResponse accountToAccountCommandResponse(Account account) {
        return AccountCommandResponse.builder()
                .accountId(account.getAccountId().toString())
                .message(ACCOUNT_CREATED_SUCCESSFUL)
                .userName(account.getUserName())
                .build();
    }


    public GetAccountResponse accountToGetAccountResponse(Account account) {
        return GetAccountResponse.builder()
                .userName(account.getUserName())
                .firstName(account.getFirstName())
                .email(account.getEmail())
                .build();
    }

    private String generateUsername(String firstName, String lastName) {
        return (firstName + "." + lastName).toLowerCase().replaceAll("\\s+", "");
    }
}
