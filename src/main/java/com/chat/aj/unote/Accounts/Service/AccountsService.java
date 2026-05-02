package com.chat.aj.unote.Accounts.Service;

import com.chat.aj.unote.Accounts.Entity.Accounts;
import com.chat.aj.unote.Accounts.Exceptions.ResourceNotFoundException;
import com.chat.aj.unote.Accounts.repository.AccountsRepository;
import com.chat.aj.unote.Accounts.request.AccountUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsService {
    private final AccountsRepository accountsRepository;

    @Override
    public Accounts getAccountById(Long accountId) throws ResourceNotFoundException {
        return accountsRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account id: " + accountId + " was not found"));
    }

    @Override
    public Accounts updateAccount(AccountUpdateRequest request, Long accountId) {

    }
}
