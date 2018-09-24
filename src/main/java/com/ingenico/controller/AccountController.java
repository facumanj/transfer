package com.ingenico.controller;

import com.ingenico.dto.AccountDto;
import com.ingenico.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path="/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(method=POST)
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
         return accountService.createAccount(accountDto);
    }
}
