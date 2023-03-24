package com.example.account.model.exception;

public class InvalidOwnerLastNameException extends RuntimeException{
    public InvalidOwnerLastNameException(String message, String id) {
        super(String.format(message,id));
    }
}
