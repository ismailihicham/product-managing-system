package com.product.managing.system.ports.input;

import com.product.managing.system.dto.order.CreateOrderCommand;
import com.product.managing.system.dto.order.OrderCommandResponse;
import com.product.managing.system.dto.order.UpdateOrderCommand;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UseCasesDomainService {

    OrderCommandResponse createOrder(CreateOrderCommand createOrderCommand);

    OrderCommandResponse updateOrder(UpdateOrderCommand updateOrderCommand);

    ProductCommandResponse createProduct(CreateProductCommand createProductCommand);

    ProductCommandResponse updateProduct(UpdateProductCommand updateProductCommand);

    ProductCommandResponse removeProduct(UUID productId);

    List<Product> retrieveAllProducts();

    Product retrieveProductInfo(UUID productId);

}
