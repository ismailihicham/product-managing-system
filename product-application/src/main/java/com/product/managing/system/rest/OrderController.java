package com.product.managing.system.rest;

import com.product.managing.system.dto.order.*;
import com.product.managing.system.ports.input.UseCasesDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping(value = "/cart", produces = "application/vnd.api.v1+json")
@RestController
public class OrderController {

    private final UseCasesDomainService useCases;

    public OrderController(UseCasesDomainService useCases) {
        this.useCases = useCases;
    }

    @PostMapping
    public ResponseEntity<OrderCommandResponse> createItemsInCart(@RequestBody OrderCommand createOrderCommand) {
        var response = useCases.createOrder(createOrderCommand);
        log.info("order created");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderCommandResponse> cancelOrderInCart(@PathVariable UUID orderId, @RequestParam UUID customerId) {
        var response = useCases.cancelOrder(CancelOrderCommand
                .builder()
                .orderId(orderId)
                .customerId(customerId).build()
        );
        log.info("order cancelled");
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<OrderCommandResponse> addItemsInCart(@RequestBody UpdateOrderCommand addItems) {
        var response = useCases.addItemsToOrder((AddItemCommand) addItems);
        log.info("order is updated with new items");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<OrderCommandResponse> removeItemsInCart(@RequestBody UpdateOrderCommand removeItems) {
        var response = useCases.removeItemsFromOrder((RemoveItemsCommand) removeItems);
        log.info("order is updated by removing some items");
        return ResponseEntity.ok(response);
    }


}
