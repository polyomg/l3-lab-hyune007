package com.poly.lab8.service;

import com.poly.lab8.dao.AccountDAO;
import com.poly.lab8.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO dao;

    @Override
    public Optional<Account> findById(String username) {
        return dao.findById(username);
    }
}
