package com.easy.bankingApp.mapper;

import com.easy.bankingApp.dto.AccountDto;
import com.easy.bankingApp.entity.Account;

public class AccountMapper {

    public static Account maptoAccount(AccountDto accountDto){
        Account account = new Account(
            accountDto.getId(),
            accountDto.getAccountHolderName(),
            accountDto.getBalance());

        return account;
    }

    public static AccountDto maptoAccountDTO(Account account){
        AccountDto accountDto = new AccountDto(
                account.getId(), account.getAccountHolderName(),
                account.getBalance() );
            return accountDto;
    }


}
