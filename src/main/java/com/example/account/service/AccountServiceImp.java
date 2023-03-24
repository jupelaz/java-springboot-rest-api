package com.example.account.service;

import com.example.account.model.Account;
import com.example.account.AccountConstants;
import com.example.account.model.dto.AccountRequest;
import com.example.account.model.exception.AccountNotFoundException;
import com.example.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepository accountRepository;
    @Override
    public Account saveAccount(AccountRequest account) {
        return accountRepository.save(account.toAccount());
    }

    @Override
    public Account readAccount(String accountId) {
        return accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND,accountId));
    }
    @Override
    public List<Account> readAllAccounts(Boolean includeActiveFlag) {
        return includeActiveFlag?
                accountRepository.findAllByActiveFlagTrue()
                :
                accountRepository.findAll();
    }

    @Override
    public Account updateAccount(String accountId, AccountRequest account) {
        if(!accountRepository.existsById(UUID.fromString(accountId)))
            throw new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND,accountId);
        var accountToSave = account.toAccount();
        accountToSave.setAccountId(UUID.fromString(accountId));
        return accountRepository.save(accountToSave);
    }

    @Override
    public Account deleteAccount(String accountId) {
        var account = accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> new AccountNotFoundException(AccountConstants.ACCOUNT_NOT_FOUND,accountId));
        account.setActiveFlag(false);
        return accountRepository.save(account);
    }

    @Override
    public List<Account> readAccountByOwnerLastName(String ownerLastName) {
        return accountRepository.findByOwnerLastName(ownerLastName);
    }

    @Override
    public List<Account> readAccountByOwnerLastNameFilterByActive(String ownerLastName) {
        return accountRepository.findByOwnerLastNameAndActiveFlagTrue(ownerLastName);
    }

    @Override
    public Optional<List<Account>> readAccountByOwnerFirstName(String ownerFirstName) {
        return accountRepository.findByOwnerFirstName(ownerFirstName);
    }




}
