package com.example.simple_wallet_api.repository;

import com.example.simple_wallet_api.entity.Account;
import com.example.simple_wallet_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findFirstByUserAndId(User user, String id);

    List<Account> findAllByUser(User user);
}
