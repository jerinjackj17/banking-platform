package com.ezybank.account.service;

import com.ezybank.account.dto.AccountResponse;
import com.ezybank.account.dto.CreateAccountRequest;
import com.ezybank.account.exception.*;
import com.ezybank.account.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AtomicLong idGenerator=new AtomicLong(1);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    public AccountResponse createAccount(CreateAccountRequest request){
        validateAmount(request.getInitialBalance());
        long id=idGenerator.getAndIncrement();
        AccountResponse account=new AccountResponse(
            id,
            "ACC"+(1000+id),
            request.getAccountHolder(),
            request.getInitialBalance()
        );
        return accountRepository.save(account);
    }

    public Collection<AccountResponse> getAllAccounts(){return accountRepository.findAll();}

    public AccountResponse getAccountById(Long id){return findAccount(id);}

    public synchronized AccountResponse deposit(Long id,BigDecimal amount){
        validateAmount(amount);
        AccountResponse current=findAccount(id);
        AccountResponse updated=new AccountResponse(
            current.getId(),
            current.getAccountNumber(),
            current.getAccountHolder(),
            current.getBalance().add(amount)
        );
        return accountRepository.save(updated);
    }

    public synchronized AccountResponse withdraw(Long id,BigDecimal amount){
        validateAmount(amount);
        AccountResponse current=findAccount(id);
        if(current.getBalance().compareTo(amount)<0){
            throw new InsufficientFundsException(id,current.getBalance(),amount);
        }
        AccountResponse updated=new AccountResponse(
            current.getId(),
            current.getAccountNumber(),
            current.getAccountHolder(),
            current.getBalance().subtract(amount)
        );
        return accountRepository.save(updated);
    }

    private AccountResponse findAccount(Long id){
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    private void validateAmount(BigDecimal amount){
        if(amount==null || amount.compareTo(BigDecimal.ZERO)<=0){
            throw new InvalidAmountException();
        }
    }
}
