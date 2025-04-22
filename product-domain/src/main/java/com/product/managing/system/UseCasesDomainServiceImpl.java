package com.product.managing.system;

import com.product.managing.system.dto.account.AccountCommandResponse;
import com.product.managing.system.dto.account.CreateAccountCommand;
import com.product.managing.system.dto.account.GetAccountResponse;
import com.product.managing.system.dto.order.CreateOrderCommand;
import com.product.managing.system.dto.order.OrderCommandResponse;
import com.product.managing.system.dto.order.UpdateOrderCommand;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.entities.Account;
import com.product.managing.system.entities.Product;
import com.product.managing.system.exception.DomainException;
import com.product.managing.system.mapper.AccountDataMapper;
import com.product.managing.system.ports.input.UseCasesDomainService;
import com.product.managing.system.ports.output.AccountRepository;
import com.product.managing.system.ports.output.ProductRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Validated
public class UseCasesDomainServiceImpl implements UseCasesDomainService {
    private final AccountDataMapper accountMapper;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;

    public static final String PROUCT_REMOVE_SUCCESSFUL = "product is removed successfully";

    public UseCasesDomainServiceImpl(
            AccountDataMapper accountMapper,
            ProductRepository productRepository,
            AccountRepository accountRepository
    ) {
        this.accountMapper = accountMapper;
        this.productRepository = productRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public OrderCommandResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public OrderCommandResponse updateOrder(UpdateOrderCommand updateOrderCommand) {
        return null;
    }

    @Override
    public ProductCommandResponse createProduct(CreateProductCommand createProductCommand) {
        findAccount(createProductCommand.getUserId());
        createProductCommand.getProduct().validateInitializeProduct();
        createProductCommand.getProduct().initializeProduct();
        var savedProduct = saveProduct(createProductCommand.getProduct());
        log.info("product is created with id {}", savedProduct.getProductId());

        return ProductCommandResponse.builder()
                .productId(savedProduct.getProductId())
                .message("Product created successfully")
                .build();
    }

    private Product saveProduct(@NotNull Product product) {
        Product productResult = productRepository.saveProduct(product);
        if (productResult == null) {
            log.error("erros occurs while saving product with id {}", product.getProductId());
            throw new DomainException("error while saving product with id" + product.getProductId());
        }
        log.info("Product has been saved successfly with id {}", productResult.getProductId());
        return productResult;
    }

    private Optional<Account> findAccount(@NotNull UUID accountId) {
        Optional<Account> account;
        try {
             account = Optional.ofNullable(accountRepository.findAccount(accountId));
        } catch (Exception e) {
            log.error("Could not find user with id {}", accountId);
            throw new DomainException("Could not find customer with id " + accountId);
        }
        return account;
    }

    @Override
    public ProductCommandResponse updateProduct(UpdateProductCommand updateProductCommand) {
        findAccount(updateProductCommand.getUserId());
        var product = findProduct(updateProductCommand.getProductId());
        var updatedP = saveProduct(product.updateProduct(updateProductCommand.getProduct()));
        log.info("product is updated successfully with id {}", updatedP.getProductId());
        return ProductCommandResponse
                .builder()
                .productId(updatedP.getProductId())
                .message("product is updated successfully")
                .build();
    }

    private Product findProduct(@NotNull UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            log.error("Could not find product with id {}", productId);
            throw new DomainException("Could not find product with id " + productId);
        }
        return product.get();
    }

    @Override
    public ProductCommandResponse removeProduct(UUID productId) {
        findProduct(productId);
        productRepository.removeProductById(productId);
        log.info("product is removed successfully {}", productId);
        return ProductCommandResponse
                .builder()
                .productId(productId)
                .message(PROUCT_REMOVE_SUCCESSFUL)
                .build();
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.retrieveAllProducts();
    }

    @Override
    public Product retrieveProductInfo(UUID productId) {
        return findProduct(productId);
    }

    @Override
    public AccountCommandResponse createAccount(CreateAccountCommand createAccountCommand) {
        var response = accountRepository.createAccount(
                accountMapper.createAccountCommandToAccount(createAccountCommand)
        );
        return accountMapper.accountToAccountCommandResponse(response);
    }

    @Override
    public GetAccountResponse findAccountInfo(UUID accountId) {
        var response = accountRepository.findAccount(accountId);
        return accountMapper.accountToGetAccountResponse(response);
    }
}
