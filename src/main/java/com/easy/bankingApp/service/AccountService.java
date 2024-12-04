package com.easy.bankingApp.service;

import com.easy.bankingApp.dto.AccountDto;
import com.easy.bankingApp.entity.Account;
import lombok.Data;
import java.util.List;


public interface AccountService {

    public AccountDto createAccount(AccountDto accountDto);

    public AccountDto getAccountById(Long id);

    public AccountDto deposit(Long id, double amount);

    public AccountDto withdraw(Long id, double amount);

    public List<AccountDto> getAllAccounts();

    public void deleteAccount(Long id);

    public  AccountDto newwithdraw(Long id, double amount);
}
