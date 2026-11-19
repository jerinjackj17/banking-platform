package com.ezybank.account.repository;
import com.ezybank.account.dto.AccountResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository {
    private final Map<Long,AccountResponse> accounts=new ConcurrentHashMap<>();
    public AccountResponse save(AccountResponse account){accounts.put(account.getId(),account);return account;}
    public Optional<AccountResponse> findById(Long id){return Optional.ofNullable(accounts.get(id));}
    public Collection<AccountResponse> findAll(){return accounts.values();}
}
