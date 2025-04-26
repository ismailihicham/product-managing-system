package com.product.managing.system.rest;

import com.product.managing.system.dto.account.AccountCommandResponse;
import com.product.managing.system.dto.account.CreateAccountCommand;
import com.product.managing.system.dto.account.GetAccountResponse;
import com.product.managing.system.ports.input.UseCasesDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping(value = "/account", produces = "application/vnd.api.v1+json")
@RestController
public class AccountController {

    private final UseCasesDomainService useCases;

    public AccountController(UseCasesDomainService useCases) {
        this.useCases = useCases;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<AccountCommandResponse> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        AccountCommandResponse response = useCases.createAccount(createAccountCommand);
        log.info(
                "create account with userName : {}  for user with id : {} is done successfully",
                response.getUserName(), response.getAccountId()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<GetAccountResponse> findAccountInfo(@PathVariable UUID accountId) {
        var response = useCases.findAccountInfo(accountId);
        log.info(
                "account with userName : {}  is retrieved : {}  successfully",
                response.getUserName()
        );
        return ResponseEntity.ok(response);

    }
}
