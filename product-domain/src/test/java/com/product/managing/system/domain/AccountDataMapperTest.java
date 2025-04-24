package com.product.managing.system.domain;

import com.product.managing.system.dto.account.CreateAccountCommand;
import com.product.managing.system.entities.Account;
import com.product.managing.system.mapper.AccountDataMapper;
import com.product.managing.system.mapper.PasswordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.product.managing.system.mapper.AccountDataMapper.ACCOUNT_CREATED_SUCCESSFUL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestConfiguration.class)
public class AccountDataMapperTest {

    @Autowired
    private AccountDataMapper accountDataMapper;

    @Autowired
    private PasswordService passwordService;

    private final static UUID ACCOUNT_ID = UUID.fromString("4e3a2d51-7f4f-4f34-9a64-2a759e8c9f21");

    @Test
    void test_createAccountCommandToAccount() {
        var command = CreateAccountCommand
                .builder()
                .firstName("Adam")
                .lastName("SMITH")
                .email("adam.smith@gmail.com")
                .password("HOHO@123")
                .build();
        var response = accountDataMapper.createAccountCommandToAccount(command);
        assertNotNull(response);
        assertEquals(response.getFirstName(), command.getFirstName());
        assertEquals(response.getEmail(), command.getEmail());
        assertEquals("adam.smith", response.getUserName());

    }
    @Test
    void test_accountToAccountCommandResponse() {

        var hashPassword = passwordService.hashPassword("HOHO@123");
        var account = Account.builder()
                .accountId(ACCOUNT_ID)
                .firstName("Adam")
                .lastName("SMITH")
                .email("adam.smith@gmail.com")
                .password(hashPassword)
                .userName("adam.smith")
                .build();
        var response = accountDataMapper.accountToAccountCommandResponse(account);
        assertNotNull(hashPassword);
        assertEquals(ACCOUNT_ID.toString(), response.getAccountId());
        assertEquals("adam.smith", response.getUserName() );
        assertEquals(ACCOUNT_CREATED_SUCCESSFUL, response.getMessage());

    }

    @Test
    void test_accountToGetAccountResponse() {

        var hashPassword = passwordService.hashPassword("HOHO@123");
        var account = Account.builder()
                .accountId(ACCOUNT_ID)
                .firstName("Adam")
                .lastName("SMITH")
                .email("adam.smith@gmail.com")
                .password(hashPassword)
                .userName("adam.smith")
                .build();

        var response = accountDataMapper.accountToGetAccountResponse(account);

        assertEquals(account.getEmail(), response.getEmail());
        assertEquals(account.getFirstName(), response.getFirstName() );
        assertEquals(account.getUserName(), response.getUserName());
    }
}
