package com.product.managing.system.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class GetAccountResponse {

    private final String firstName;

    private final String userName;

    private final String email;
}
