package com.ingenico.controller;

import com.ingenico.dto.TransferDto;
import com.ingenico.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path="/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @RequestMapping(method=POST)
    public TransferDto makeTransfer(@RequestBody TransferDto transferDto) {
        return transferService.transferMoney(transferDto);
    }
}
