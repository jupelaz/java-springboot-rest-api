package com.example.account.controller;

import com.example.account.model.dto.AccountErrorResponse;
import com.example.account.model.exception.AccountInvalidException;
import com.example.account.model.exception.AccountNotFoundException;
import com.example.account.model.exception.InvalidOwnerLastNameException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AccountControllerExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountNotFoundException(RuntimeException re) {
        return new ResponseEntity<>(
                new AccountErrorResponse(re.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccountInvalidException.class)
    public ResponseEntity<AccountErrorResponse> handleAccountInvalidException(RuntimeException re) {
        return new ResponseEntity<>(new AccountErrorResponse(re.getMessage()),
                HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InvalidOwnerLastNameException.class)
    public ResponseEntity<AccountErrorResponse> handleInvalidOwnerLastNameException(RuntimeException re) {
        return new ResponseEntity<>(new AccountErrorResponse(re.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return new ResponseEntity<>(
                Map.of("errors",errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AccountErrorResponse> handleGenericException(Exception e) {

        return new ResponseEntity<>(new AccountErrorResponse(e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}