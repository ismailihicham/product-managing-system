package com.product.managing.system.dto.account;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CreateAccountCommand {

    @NotNull
    private final String firstName;

    @NotNull
    private final String lastName;

    @NotNull
    private final String password;

    @NotNull
    private final String email;

}
