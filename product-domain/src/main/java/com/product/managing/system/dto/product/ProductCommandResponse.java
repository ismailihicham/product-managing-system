package com.product.managing.system.dto.product;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCommandResponse {

    @NotNull
    private UUID productId;
    @NotNull
    private String message;
}
