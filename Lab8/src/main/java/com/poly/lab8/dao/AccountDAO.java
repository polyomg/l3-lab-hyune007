package com.poly.lab8.dao;

import com.poly.lab8.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountDAO extends JpaRepository<Account, String> {
}
