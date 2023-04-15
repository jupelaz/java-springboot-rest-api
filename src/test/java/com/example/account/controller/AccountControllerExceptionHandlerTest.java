package com.example.account.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountControllerExceptionHandlerTest {
    @InjectMocks AccountControllerExceptionHandler accountControllerExceptionHandler;
    @Test
    public void handleAccountNotFoundException(){
        var re = Mockito.mock(RuntimeException.class);
        when(re.getMessage()).thenReturn("error");
        var response = accountControllerExceptionHandler.handleAccountNotFoundException(re);
        assertEquals(response.getBody().getMessage(),"error");
        assertEquals(response.getStatusCode(),HttpStatus.CONFLICT);
    }

    @Test
    public void handleAccountInvalidException(){
        var re = Mockito.mock(RuntimeException.class);
        when(re.getMessage()).thenReturn("error");
        var response = accountControllerExceptionHandler.handleAccountInvalidException(re);
        assertEquals(response.getBody().getMessage(),"error");
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void handleInvalidOwnerLastNameException(){
        var re = Mockito.mock(RuntimeException.class);
        when(re.getMessage()).thenReturn("error");
        var response = accountControllerExceptionHandler.handleInvalidOwnerLastNameException(re);
        assertEquals(response.getBody().getMessage(),"error");
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }
    @Test
    public void handleGenericException(){
        var re = Mockito.mock(RuntimeException.class);
        when(re.getMessage()).thenReturn("error");
        var response = accountControllerExceptionHandler.handleGenericException(re);
        assertEquals(response.getBody().getMessage(),"error");
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
