package com.example.account.model.dto;

import com.example.account.model.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountRequest {
    @Positive(message = "Balance should be positive")
    @NotNull(message = "Balance should be informed")
    private Double balance;
    @NotBlank(message = "First name should not be blank")
    private String firstName;
    @NotBlank(message = "Last name should not be blank")
    private String lastName;

    public Account toAccount(){
        Account account = new Account();
        account.setBalance(this.balance);
        account.setOwnerFirstName(this.getFirstName());
        account.setOwnerLastName(this.getLastName());
        return account;
    }
}
