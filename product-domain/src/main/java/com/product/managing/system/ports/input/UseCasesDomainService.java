package com.product.managing.system.ports.input;

import com.product.managing.system.dto.order.CreateOrderCommand;
import com.product.managing.system.dto.order.OrderCommandResponse;
import com.product.managing.system.dto.order.UpdateOrderCommand;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;

public interface UseCasesDomainService {

    OrderCommandResponse createOrder(CreateOrderCommand createOrderCommand);

    OrderCommandResponse updateOrder(UpdateOrderCommand updateOrderCommand);

    ProductCommandResponse createProduct(CreateProductCommand createProductCommand);

    ProductCommandResponse updateProduct(UpdateProductCommand updateProductCommand);

}
