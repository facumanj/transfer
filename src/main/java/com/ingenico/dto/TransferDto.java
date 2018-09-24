package com.ingenico.dto;

import java.math.BigDecimal;

public class TransferDto {
    private Long id;
    private Long accountFrom;
    private Long accountTo;
    private BigDecimal money;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Long accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Long getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Long accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferDto that = (TransferDto) o;

        if (!accountFrom.equals(that.accountFrom)) return false;
        if (!accountTo.equals(that.accountTo)) return false;
        return money.equals(that.money);
    }

    @Override
    public int hashCode() {
        int result = accountFrom.hashCode();
        result = 31 * result + accountTo.hashCode();
        result = 31 * result + money.hashCode();
        return result;
    }
}
