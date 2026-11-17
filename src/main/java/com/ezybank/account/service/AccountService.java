package com.ezybank.account.service;

import com.ezybank.account.dto.AccountResponse;
import com.ezybank.account.dto.CreateAccountRequest;
import com.ezybank.account.repository.AccountRepository;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger log =
            LoggerFactory.getLogger(AccountService.class);

    private final AtomicLong idGenerator = new AtomicLong(1);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request) {

        long id = idGenerator.getAndIncrement();

        String accountNumber = "ACC" + (1000 + id);

        AccountResponse account = new AccountResponse(
                id,
                accountNumber,
                request.getAccountHolder(),
                request.getInitialBalance()
        );

        log.info(
                "Creating account {} for holder {}",
                accountNumber,
                request.getAccountHolder()
        );

        return accountRepository.save(account);
    }

    public Collection<AccountResponse> getAllAccounts() {
        return accountRepository.findAll();
    }
}