package com.example.account.repository;


import com.example.account.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Collections;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository repository;

    Account account;
    @BeforeEach public void before() {
        account = new Account();
        account.setBalance(1.0);
        account.setOwnerFirstName("John");
        account.setOwnerLastName("Doe");
        account.setActiveFlag(true);
    }
    @Test
    public void findEmpty(){
        var accounts = repository.findAll();
        assertEquals(accounts,
                Collections.EMPTY_LIST,
                "Should be no accounts if repository is empty");
    }
    @Test
    public void findAny(){
        repository.save(account);
        assertNotEquals(repository.findAll(), Collections.EMPTY_LIST,
                "should be an Account if an Account is saved");
    }
    @Test
    public void findOne(){
        Account returnedAccount = repository.save(account);
        assertNotNull(
                repository.findById(returnedAccount.getAccountId()).orElse(null),
                "There should be a Account with this id");
        var randomId = UUID.randomUUID();
        while(returnedAccount.getAccountId().equals(randomId)){
            randomId = UUID.randomUUID();
        }
        assertNull(
                repository.findById(UUID.randomUUID()).orElse(null),
                "There should be no Account with a random id");
    }
    @Test
    public void save(){
        var account = repository.save(this.account);

        assertEquals(this.account.getBalance(),account.getBalance(),"Balance should be equal");
        assertEquals(this.account.getOwnerFirstName(),account.getOwnerFirstName(),"Owner first name should be equal");
        assertEquals(this.account.getOwnerLastName(),account.getOwnerLastName(),"Owner last name should be equal");
        assertEquals(this.account.isActiveFlag(),account.isActiveFlag(),"Active flag should be equal");
    }
    @Test
    public void update(){
        var accountSaved = repository.save(account);
        accountSaved.setBalance(2.0);
        var id = accountSaved.getAccountId();
        repository.save(accountSaved);
        var foundAccount = repository.findById(id).orElse(null);
        assert foundAccount != null;
        assertEquals(foundAccount.getBalance(),2.0,"Balance should be the updated one");
    }
    @Test
    public void delete(){
        var accountSaved = repository.save(account);
        var id = accountSaved.getAccountId();
        assertNotNull(repository.findById(id).orElse(null),"new account should exist");
        repository.delete(accountSaved);
        assertNull(repository.findById(id).orElse(null),"Account should be deleted");
    }
}
