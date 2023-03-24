package com.example.account.model;

import com.example.account.model.dto.AccountResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Entity
@Data
public class Account {
    @Id @GeneratedValue private UUID accountId;
    private Double balance;
    private String ownerFirstName;
    private String ownerLastName;
    private boolean activeFlag = true;

    public AccountResponse toAccountResponse(){
        return AccountResponse.builder()
                .balance(this.balance)
                .FirstName(this.ownerFirstName)
                .LastName(this.ownerLastName).build();
    }
}
