package com.easy.bankingApp.service.impl;

import com.easy.bankingApp.dto.AccountDto;
import com.easy.bankingApp.entity.Account;
import com.easy.bankingApp.mapper.AccountMapper;
import com.easy.bankingApp.repository.AccountRepository;
import com.easy.bankingApp.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        System.out.println("inside service");
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDTO(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        return AccountMapper.maptoAccountDTO(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        accountRepository.save(account);
        AccountDto accountDto=AccountMapper.maptoAccountDTO(account);
        return accountDto;
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        double total = account.getBalance() - amount;
        account.setBalance(total);
        accountRepository.save(account);
        AccountDto accountDto=AccountMapper.maptoAccountDTO(account);
        return accountDto;
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List accountList = accountRepository.findAll();
        //streams
        return (List<AccountDto>) accountList.stream().map((account) -> AccountMapper.maptoAccountDTO((Account) account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
    }

    @Override
    public  AccountDto newwithdraw(Long id, double amount){
        Account account =  accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exist"));

        double newbalance = account.getBalance() - amount;
      //  if(newbalance <0){
       //     Throw new RuntimeException("Insufficient balance");
      //  }
        account.setBalance(newbalance);
        Account modifiedAccount =accountRepository.save(account);
        AccountDto accountDto = AccountMapper.maptoAccountDTO(modifiedAccount);
        return accountDto;
    }
}
