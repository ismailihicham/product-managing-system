package com.product.managing.system.entities;

import com.product.managing.system.exception.DomainException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class Account {
    private UUID accountId;
    private String firstName;
    private String lastName;
    private String email;

    public void validateAccountPersonalInfo() {
        if (this.firstName == null || this.lastName == null || this.email == null) {
            throw new DomainException("the account with id" + this.accountId + "need required data to be validated");
        }
    }
}
