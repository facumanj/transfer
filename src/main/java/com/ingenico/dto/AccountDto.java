package com.ingenico.dto;

import java.math.BigDecimal;

public class AccountDto {
    private Long id;
    private String name;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountDto that = (AccountDto) o;

        if (!name.equals(that.name)) return false;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
