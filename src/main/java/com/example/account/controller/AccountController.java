package com.example.account.controller;

import com.example.account.model.dto.AccountRequest;
import com.example.account.model.dto.AccountResponse;
import com.example.account.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody AccountRequest accountRequest) {
        var account = accountService.saveAccount(accountRequest);
        return ResponseEntity.ok(account.toAccountResponse());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable UUID id, @Valid @RequestBody AccountRequest accountRequest) throws ArrayIndexOutOfBoundsException {
        accountService.updateAccount(String.valueOf(id), accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(AccountResponse.builder().build());
    }

    @GetMapping("/{id}/account")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID id) {
        var account = accountService.readAccount(String.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).body(account.toAccountResponse());
    }

}

