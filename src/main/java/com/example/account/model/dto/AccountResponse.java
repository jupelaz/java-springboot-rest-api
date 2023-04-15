package com.example.account.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountResponse {
    private Double balance;
    private String FirstName;
    private String LastName;
}
