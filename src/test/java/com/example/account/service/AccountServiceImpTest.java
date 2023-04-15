package com.example.account.service;

import com.example.account.model.Account;
import com.example.account.AccountConstants;
import com.example.account.model.dto.AccountRequest;
import com.example.account.model.exception.AccountNotFoundException;
import com.example.account.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImp accountServiceImp;

    @AfterEach
    public void after() {
        Mockito.verifyNoMoreInteractions(accountRepository);
        Mockito.reset(accountRepository);
    }

    @Test
    void saveAccount() {
        var account = Mockito.mock(Account.class);
        var accountRequest = Mockito.mock(AccountRequest.class);
        when(accountRepository.save(account)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(accountRequest.toAccount()).thenReturn(account);

        var response = accountServiceImp.saveAccount(accountRequest);

        assertEquals(account, response);
        Mockito.verify(accountRepository).save(account);
    }

    @Test
    void readAccount() {
        var account = Mockito.mock(Account.class);
        var id = UUID.randomUUID();
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        UUID anotherId = UUID.randomUUID();
        var response = accountServiceImp.readAccount(id.toString());
        assertEquals(account,response);
        Mockito.verify(accountRepository).findById(id);

        var thrown = assertThrows(
                AccountNotFoundException.class,
                () -> accountServiceImp.readAccount(anotherId.toString()));
        assertEquals(thrown.getMessage(), String.format(AccountConstants.ACCOUNT_NOT_FOUND, anotherId));
        Mockito.verify(accountRepository).findById(anotherId);

    }

    @Test
    void readAllAccounts() {
        var account = Mockito.mock(Account.class);
        when(accountRepository.findAllByActiveFlagTrue()).thenReturn(List.of(account));
        when(accountRepository.findAll()).thenReturn(List.of(account));
        var response = accountServiceImp.readAllAccounts(true);
        Mockito.verify(accountRepository).findAllByActiveFlagTrue();
        assertEquals(response,List.of(account));
        var response2 = accountServiceImp.readAllAccounts(false);
        assertEquals(response2,List.of(account));
        Mockito.verify(accountRepository).findAll();
    }

    @Test
    void updateAccountOK() {
        var id = UUID.randomUUID();
        var account = Mockito.mock(Account.class);
        var accountRequest = Mockito.mock(AccountRequest.class);
        account.setAccountId(id);
        when(accountRepository.existsById(id)).thenReturn(true);
        when(accountRequest.toAccount()).thenReturn(account);
        when(accountRepository.save(account)).thenReturn(account);

        var response = accountServiceImp.updateAccount(id.toString(),accountRequest);
        assertEquals(account,response);
        Mockito.verify(accountRepository).existsById(id);
        Mockito.verify(accountRepository).save(account);
    }

    @Test
    void updateAccountError() {
        var id = UUID.randomUUID();
        var accountRequest = Mockito.mock(AccountRequest.class);
        when(accountRepository.existsById(id)).thenReturn(false);
        var thrown = assertThrows(
                AccountNotFoundException.class,
                () -> accountServiceImp.updateAccount(id.toString(),
                        accountRequest));
        Mockito.verify(accountRepository).existsById(id);
        assertEquals(thrown.getMessage(),String.format(AccountConstants.ACCOUNT_NOT_FOUND,id));
    }

    @Test
    void deleteAccount() {
        var id = UUID.randomUUID();
        var account = Mockito.mock(Account.class);
        when(accountRepository.findById(id)).thenReturn(Optional.of(account));
        when(accountRepository.save(account)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        var response = accountServiceImp.deleteAccount(id.toString());
        assertEquals(account.getAccountId(),response.getAccountId());
        assertFalse(response.isActiveFlag());
        Mockito.verify(accountRepository).findById(id);
        Mockito.verify(accountRepository).save(account);
    }

    @Test
    void readAccountByOwnerLastName() {
        var account = Mockito.mock(Account.class);
        var lastName = "";
        when(accountRepository.findByOwnerLastName(lastName)).thenReturn(List.of(account));
        var response = accountServiceImp.readAccountByOwnerLastName(lastName);
        assertEquals(List.of(account),response);
        Mockito.verify(accountRepository).findByOwnerLastName(lastName);
    }

    @Test
    void readAccountByOwnerLastNameFilterByActive() {
        var account = Mockito.mock(Account.class);
        var lastName = "";
        when(accountRepository.findByOwnerLastNameAndActiveFlagTrue(lastName)).thenReturn(List.of(account));
        var response = accountServiceImp.readAccountByOwnerLastNameFilterByActive(lastName);
        assertEquals(List.of(account),response);
        Mockito.verify(accountRepository).findByOwnerLastNameAndActiveFlagTrue(lastName);
    }

    @Test
    void readAccountByOwnerFirstName() {
        var account = Mockito.mock(Account.class);
        var firstName = "";
        when(accountRepository.findByOwnerFirstName(firstName)).thenReturn(Optional.of(List.of(account)));
        var response = accountServiceImp.readAccountByOwnerFirstName(firstName);
        assertEquals(Optional.of(List.of(account)),response);
        Mockito.verify(accountRepository).findByOwnerFirstName(firstName);
    }
}