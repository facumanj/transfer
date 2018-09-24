package com.ingenico.dto;

import com.ingenico.domain.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferDtoTransformer {
    public TransferDto entityToDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setAccountFrom(transfer.getAccountFrom().getId());
        transferDto.setAccountTo(transfer.getAccountTo().getId());
        transferDto.setMoney(transfer.getMoney());

        return transferDto;
    }
}
