package com.product.managing.system.domain;

import com.product.managing.system.dto.order.CancelOrderCommand;
import com.product.managing.system.dto.order.OrderCommand;
import com.product.managing.system.dto.order.UpdateOrderCommand;
import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.entities.*;
import com.product.managing.system.mapper.AccountDataMapper;
import com.product.managing.system.mapper.OrderDataMapper;
import com.product.managing.system.ports.input.UseCasesDomainService;
import com.product.managing.system.ports.output.AccountRepository;
import com.product.managing.system.ports.output.OrderRepository;
import com.product.managing.system.ports.output.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.product.managing.system.UseCasesDomainServiceImpl.ORDER_CANCEL_SUCCESSFUL;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TestConfiguration.class)
public class UseCasesDomainServiceTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDataMapper orderDataMapper;

    @Autowired
    private AccountDataMapper accountDataMapper;

    @Autowired
    private UseCasesDomainService useCasesDomainService;

    private CreateProductCommand createProductCommand;
    private OrderCommand createOrderCommand;
    private Order order;
    private Product product;
    private final static Money PRODUCT_PRICE = new Money(BigDecimal.valueOf(200.00));
    private Account account;
    private final static UUID USER_ID = UUID.fromString("4e3a2d51-7f4f-4f34-9a64-2a759e8c9f21");
    private final static UUID PRODUCT_ID = UUID.fromString("b5cd1c4c-206e-4b64-9e75-cf1c4d2f1c2e");
    private final static UUID ORDER_ID = UUID.fromString("c7d42a91-f30e-4c18-8a62-1e3f4c3a7dcd");

    @BeforeAll
    public void init() {
        account = Account.builder()
                .accountId(USER_ID)
                .firstName("Jean")
                .lastName("PEREIRA")
                .userName("jean.pereira")
                .email("jean.pereira@gmail.com")
                .build();
        product = Product.builder()
                .productId(PRODUCT_ID)
                .name("product-1")
                .category("food")
                .code("AA_1122")
                .price(PRODUCT_PRICE)
                .quantity(1)
                .createdAt(ZonedDateTime.of(
                        LocalDateTime.of(2024, 10, 24, 10, 00),
                        ZoneId.of("UTC")
                ))
                .build();
        createProductCommand = CreateProductCommand.builder()
                .product(product)
                .build();
        order = Order.builder()
                .customerId(USER_ID)
                .orderId(ORDER_ID)
                .price(new Money(BigDecimal.valueOf(400.00)))
                .items(List.of(
                        OrderItem.builder()
                                .quantity(2)
                                .product(product)
                                .orderId(ORDER_ID)
                                .subTotal(new Money(BigDecimal.valueOf(400.00)))
                                .price(PRODUCT_PRICE)
                                .build()))
                .build();
        createOrderCommand = OrderCommand.builder()
                .order(order)
                .customerId(USER_ID)
                .build();

        when(accountRepository.findAccount(USER_ID)).thenReturn(account);
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.saveProduct(product)).thenReturn(product);
        ProductCommandResponse response = useCasesDomainService.createProduct(createProductCommand);
        assertNotNull(response.getProductId());
        assertEquals(product.getProductId(), response.getProductId());

    }

    @Test
    public void testUpdateProduct() {
        var productUp = Product.builder()
                .name("product-updated")
                .code("BB_1122")
                .category("gaming")
                .build();
        UpdateProductCommand updateProductCommand = UpdateProductCommand
                .builder()
                .productId(PRODUCT_ID)
                .product(productUp)
                .build();
        var p = product.updateProduct(updateProductCommand);

        when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.ofNullable(product));
        when(productRepository.modifyProduct(any())).thenReturn(p);

        var response = useCasesDomainService.updateProduct(updateProductCommand);

        assertEquals("product-updated", p.getName());
        assertEquals("BB_1122", p.getCode());
        assertEquals(PRODUCT_ID, p.getProductId());
        assertEquals("product is updated successfully", response.getMessage());
        assertEquals(PRODUCT_ID, response.getProductId());
    }

    @Test
    public void testCreateOrder() {
        when(accountRepository.findAccount(USER_ID)).thenReturn(account);
        when(orderDataMapper.orderCommandToOrder(any())).thenReturn(order);
        when(orderRepository.saveOrder(any())).thenReturn(order);
        var response = useCasesDomainService.createOrder(createOrderCommand);
        assertNotNull(response.getOrderId());
        assertEquals(OrderStatus.APPROVED, response.getStatus());
        assertEquals("ORDER APPROVE AND SAVED", response.getMessage());
    }

    @Test
    public void test_cancelOrder() {
        var cancelOrderCommand = CancelOrderCommand
                .builder()
                .orderId(ORDER_ID)
                .customerId(USER_ID)
                .build();
        doNothing().when(orderRepository).cancelOrder(ORDER_ID);
        var response = useCasesDomainService.cancelOrder(cancelOrderCommand);
        assertEquals(OrderStatus.CANCELLED, response.getStatus());
        assertEquals(ORDER_CANCEL_SUCCESSFUL, response.getMessage());
        assertEquals(ORDER_ID, response.getOrderId());

    }

    @Test
    public void test_addItemsToOrder() {
        var addItemCommand = UpdateOrderCommand
                .builder()
                .items(List.of(
                                OrderItem.builder()
                                        .quantity(1)
                                        .product(product)
                                        .orderId(ORDER_ID)
                                        .subTotal(PRODUCT_PRICE)
                                        .price(PRODUCT_PRICE)
                                        .build()))
                .customerId(USER_ID)
                .order(order)
                .build();
        var newOrder = order.addItemToOrder(addItemCommand.getItems());
        var newOrderPrice = new Money(BigDecimal.valueOf(400.00)).add(PRODUCT_PRICE);

        assertEquals(2, newOrder.getItems().size());
        assertEquals(newOrderPrice.getAmount(), newOrder.getPrice().getAmount());
    }

    @Test
    public void test_removeItemsToOrder() {
        var order = Order.builder()
                .customerId(USER_ID)
                .orderId(ORDER_ID)
                .price(new Money(BigDecimal.valueOf(400.00)))
                .items(List.of(
                        OrderItem.builder()
                                .quantity(2)
                                .product(product)
                                .orderId(ORDER_ID)
                                .subTotal(new Money(BigDecimal.valueOf(400.00)))
                                .price(PRODUCT_PRICE)
                                .build()))
                .build();
        var addItemCommand = UpdateOrderCommand
                .builder()
                .items(List.of(
                        OrderItem.builder()
                                .quantity(1)
                                .product(product)
                                .orderId(ORDER_ID)
                                .subTotal(PRODUCT_PRICE)
                                .price(PRODUCT_PRICE)
                                .build()))
                .customerId(USER_ID)
                .order(order)
                .build();
        var newOrder = order.removeFromOrder(addItemCommand.getItems());
        var newOrderPrice = new Money(BigDecimal.valueOf(400.00)).subtract(PRODUCT_PRICE);

        assertEquals(1, newOrder.getItems().size());
        assertEquals(newOrderPrice.getAmount(), newOrder.getPrice().getAmount());
    }
}
