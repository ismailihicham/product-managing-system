package com.product.managing.system.ports.input;

import com.product.managing.system.dto.account.AccountCommandResponse;
import com.product.managing.system.dto.account.CreateAccountCommand;
import com.product.managing.system.dto.account.GetAccountResponse;
import com.product.managing.system.dto.order.*;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.entities.Product;

import java.util.List;
import java.util.UUID;

public interface UseCasesDomainService {

    OrderCommandResponse createOrder(OrderCommand createOrderCommand);

    OrderCommandResponse cancelOrder(CancelOrderCommand cancelOrderCommand);

    OrderCommandResponse addItemsToOrder(AddItemCommand updateOrderCommand);

    OrderCommandResponse removeItemsFromOrder(RemoveItemsCommand updateOrderCommand);

    ProductCommandResponse createProduct(CreateProductCommand createProductCommand);

    ProductCommandResponse updateProduct(UpdateProductCommand updateProductCommand);

    ProductCommandResponse removeProduct(UUID productId);

    List<Product> retrieveAllProducts();

    Product retrieveProductInfo(UUID productId);

    AccountCommandResponse createAccount(CreateAccountCommand createAccountCommand);

    GetAccountResponse findAccountInfo(UUID accountId);

}
