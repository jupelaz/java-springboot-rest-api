package com.example.account.repository;

import com.example.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> findAllByActiveFlagTrue();
    List<Account> findByOwnerLastName(String ownerLastName);
    List<Account> findByOwnerLastNameAndActiveFlagTrue(String ownerLastName);
    Optional<List<Account>> findByOwnerFirstName(String ownerFirstName);
}
