package com.poly.lab8.service;

import com.poly.lab8.entity.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> findById(String username);
}
