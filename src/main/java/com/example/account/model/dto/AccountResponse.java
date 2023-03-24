package com.example.account.model.dto;

import com.example.account.model.Account;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountResponse {
    private Double balance;
    private String FirstName;
    private String LastName;

    public Account toAccount(){
        Account account = new Account();
        account.setBalance(this.balance);
        account.setOwnerFirstName(this.getFirstName());
        account.setOwnerLastName(this.getLastName());
        return account;
    }
}
