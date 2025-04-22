package com.product.managing.system.rest;

import com.product.managing.system.dto.product.CreateProductCommand;
import com.product.managing.system.dto.product.ProductCommandResponse;
import com.product.managing.system.dto.product.UpdateProductCommand;
import com.product.managing.system.entities.Product;
import com.product.managing.system.model.ModifyProductQuery;
import com.product.managing.system.ports.input.UseCasesDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.product.managing.system.UseCasesDomainServiceImpl.PROUCT_REMOVE_SUCCESSFUL;

@Slf4j
@RequestMapping(value = "/products", produces = "application/vnd.api.v1+json")
@RestController
public class ProductController {

    private final UseCasesDomainService useCases;

    public ProductController(UseCasesDomainService useCases) {
        this.useCases = useCases;
    }

    @PostMapping
    public ResponseEntity<ProductCommandResponse> createOrder(@RequestBody CreateProductCommand createProductCommand) {
        ProductCommandResponse response = useCases.createProduct(createProductCommand);
        log.info(
                "create product : {}  for user : {}",
                response.getProductId(), createProductCommand.getUserId()
        );
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductCommandResponse> modifyProduct(
            @PathVariable UUID productId,
            @RequestBody ModifyProductQuery modifyProductQuery
    ) {
        var updateProductCommand = UpdateProductCommand
                .builder()
                .productId(productId)
                .product(modifyProductQuery.getProduct())
                .userId(modifyProductQuery.getUserId())
                .build();

        ProductCommandResponse response = useCases.updateProduct(updateProductCommand);
        log.info(
                "product with id : {}  is updated successfully with new id {}",
                updateProductCommand.getProductId(), response.getProductId()
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeProduct(@PathVariable UUID productId) {
        var response = useCases.removeProduct(productId);
        if (PROUCT_REMOVE_SUCCESSFUL.equals(response.getMessage())) {

        }
        log.info(
                "product with id : {}  is removed successfully with new id {}",
                response.getProductId()
        );
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Product>> retrieveAllProducts() {
        var response = useCases.retrieveAllProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> retrieveProductInfo(@PathVariable UUID productId) {
        var response = useCases.retrieveProductInfo(productId);
        return ResponseEntity.ok(response);
    }
}
