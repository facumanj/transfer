package com.ingenico.service;

import com.ingenico.domain.Account;
import com.ingenico.dto.AccountDto;
import com.ingenico.dto.AccountDtoTransformer;
import com.ingenico.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountDtoTransformer accountDtoTransformer;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        if( accountDto.getBalance().signum() < 0)
            throw new TransferException("Balance cannot be negative");

        Account account = accountDtoTransformer.dtoToEntity(accountDto);

        account = accountRepository.save(account);

        return accountDtoTransformer.entityToDto(account);
    }
}