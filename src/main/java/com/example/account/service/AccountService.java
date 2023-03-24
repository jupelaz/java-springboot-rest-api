package com.example.account.service;

import com.example.account.model.Account;
import com.example.account.model.dto.AccountRequest;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account saveAccount(AccountRequest account);
    Account readAccount(String accountId);
    List<Account> readAllAccounts(Boolean includeActiveFlag);
    Account updateAccount(String accountId, AccountRequest account);
    Account deleteAccount(String accountId);
    List<Account> readAccountByOwnerLastName(String ownerLastName);
    List<Account> readAccountByOwnerLastNameFilterByActive(String ownerLastName);
    Optional<List<Account>> readAccountByOwnerFirstName(String ownerFirstName);
}
