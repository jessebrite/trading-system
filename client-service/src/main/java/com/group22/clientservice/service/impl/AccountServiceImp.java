package com.group22.clientservice.service.impl;

import com.group22.clientservice.model.Account;
import com.group22.clientservice.repository.AccountRepository;
import com.group22.clientservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImp implements AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    @Override
    public Account createNewAccount(Account account) {
        return accountRepository.save(account);
    }
}
