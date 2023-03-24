package com.example.account.model.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message,String id) {
        super(String.format(message,id));
    }
}
