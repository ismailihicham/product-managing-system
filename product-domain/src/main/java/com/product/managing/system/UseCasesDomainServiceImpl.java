package com.product.managing.system;

import com.product.managing.system.dto.order.CreateOrderCommand;
import com.product.managing.system.dto.order.OrderCommandResponse;
import com.product.managing.system.dto.order.UpdateOrderCommand;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.ports.input.UseCasesDomainService;

public class UseCasesDomainServiceImpl implements UseCasesDomainService {
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
        return null;
    }

    @Override
    public ProductCommandResponse updateProduct(UpdateProductCommand updateProductCommand) {
        return null;
    }
}
