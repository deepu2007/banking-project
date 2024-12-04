package com.easy.bankingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AccountDto {

    private Long id;

    private String accountHolderName;

    private double balance;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }




}