package com.ingenico.dto;

import com.ingenico.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoTransformer {

    public Account dtoToEntity(AccountDto accountDto) {
        Account account = new Account();

        account.setName(accountDto.getName());
        account.setBalance(accountDto.getBalance());

        return account;
    }

    public AccountDto entityToDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setName(account.getName());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }
}
