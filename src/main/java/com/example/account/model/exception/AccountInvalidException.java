package com.example.account.model.exception;

public class AccountInvalidException extends RuntimeException{
    public AccountInvalidException(String message, String id) {
        super(String.format(message,id));
    }
}
