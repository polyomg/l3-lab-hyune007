package com.poly.lab6.service;

import org.springframework.data.jpa.repository.JpaRepository;
import com.poly.lab6.entity.Account;

public interface AccountService extends JpaRepository<Account, String> {
}