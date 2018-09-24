package com.ingenico.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Transfer {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name="account_from")
    private Account accountFrom;

    @OneToOne
    @JoinColumn(name="account_to")
    private Account accountTo;

    private BigDecimal money;

    @Version
    private Integer version;

    public Transfer(Account accountFrom, Account accountTo, BigDecimal money) {
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
