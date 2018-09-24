package com.ingenico.service;

import com.ingenico.domain.Account;
import com.ingenico.domain.Transfer;
import com.ingenico.dto.TransferDto;
import com.ingenico.dto.TransferDtoTransformer;
import com.ingenico.exception.TransferException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransferServiceImpl implements TransferService {

    private static final String ACCOUNT_NOT_FOUND = "Account ID %s not found";

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransferDtoTransformer transferDtoTransformer;

    @Override
    @Transactional
    public TransferDto transferMoney(TransferDto transferDto) {
        if( transferDto.getMoney().signum() < 0 )
            throw new TransferException("Money to transfer cannot be negative");

        Optional<Account> optAccountFrom = accountRepository.findById(transferDto.getAccountFrom());
        Optional<Account> optAccountTo = accountRepository.findById(transferDto.getAccountTo());

        if( !optAccountFrom.isPresent())
            throw new TransferException(String.format(ACCOUNT_NOT_FOUND, transferDto.getAccountFrom()));

        if( !optAccountTo.isPresent())
            throw new TransferException(String.format(ACCOUNT_NOT_FOUND, transferDto.getAccountTo()));

        Account accountFrom = optAccountFrom.get();
        Account accountTo = optAccountTo.get();

        Transfer transfer = commitTransfer(accountFrom, accountTo, transferDto.getMoney());

        return transferDtoTransformer.entityToDto(transfer);
    }

    public Transfer commitTransfer(Account accountFrom, Account accountTo, BigDecimal money) {
        if( accountFrom.getBalance().compareTo(money) < 0 )
            throw new TransferException("Money to transfer exceeds balance of account");

        Transfer transfer = transferRepository.save(new Transfer(accountFrom, accountTo, money));

        accountFrom.setBalance(accountFrom.getBalance().subtract(money));
        accountTo.setBalance(accountTo.getBalance().add(money));

        accountRepository.save(accountFrom);
        accountRepository.save(accountTo);

        return transfer;
    }
}
