package com.example.account.controller;

import com.example.account.model.Account;
import com.example.account.model.dto.AccountRequest;
import com.example.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
    @Mock AccountService accountService;
    @InjectMocks
    AccountController accountController;

    @Test
    public void createAccount() {
        var accountRequest = Mockito.mock(AccountRequest.class);
        var account = Mockito.mock(Account.class);
        when(accountService.saveAccount(accountRequest)).thenReturn(account);
        accountController.createAccount(accountRequest);
        Mockito.verify(accountService).saveAccount(accountRequest);
    }

    @Test
    public void updateAccount() {
        var accountRequest = Mockito.mock(AccountRequest.class);
        var account = Mockito.mock(Account.class);
        UUID id = UUID.randomUUID();
        when(accountService.updateAccount(id.toString(),accountRequest)).thenReturn(account);
        accountController.updateAccount(id,accountRequest);
        Mockito.verify(accountService).updateAccount(id.toString(),accountRequest);
    }

    @Test
    public void getAccount() {
        var account = Mockito.mock(Account.class);

        UUID id = UUID.randomUUID();
        when(accountService.readAccount(id.toString())).thenReturn(account);
        accountController.getAccount(id);
        Mockito.verify(accountService).readAccount(id.toString());
    }

}
