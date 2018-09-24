package com.ingenico;

import com.ingenico.dto.AccountDto;
import com.ingenico.dto.TransferDto;

public class Cloner {

    public static AccountDto getCloneFrom(AccountDto accountDto) {
        AccountDto clone = new AccountDto();

        clone.setId(accountDto.getId());
        clone.setName(accountDto.getName());
        clone.setBalance(accountDto.getBalance());

        return clone;
    }

    public static TransferDto getCloneFrom(TransferDto transferDto) {
        TransferDto clone = new TransferDto();

        clone.setId(transferDto.getId());
        clone.setAccountFrom(transferDto.getAccountFrom());
        clone.setAccountTo(transferDto.getAccountTo());
        clone.setMoney(transferDto.getMoney());

        return clone;
    }
}
