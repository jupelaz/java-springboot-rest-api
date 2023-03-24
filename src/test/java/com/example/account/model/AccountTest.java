package com.example.account.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {
    Account a;
    UUID id;

    @BeforeEach
    public void before(){
        id = UUID.randomUUID();
        a = new Account();
        a.setAccountId(id);
        a.setBalance(1.0);
        a.setOwnerFirstName("John");
        a.setOwnerLastName("Doe");
        a.setActiveFlag(true);
    }
    @Test
    void getAccountId() {
        assertEquals(a.getAccountId(),id);
    }

    @Test
    void getBalance() {
        assertEquals(1.0,a.getBalance());
    }

    @Test
    void getOwnerFirstName() {
        assertEquals(a.getOwnerFirstName(),"John");
    }

    @Test
    void getOwnerLastName() {
        assertEquals(a.getOwnerLastName(),"Doe");
    }

    @Test
    void isActiveFlag() {
        assertTrue(a.isActiveFlag());
    }
}