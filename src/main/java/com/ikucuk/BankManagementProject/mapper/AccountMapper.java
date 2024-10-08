package com.ikucuk.BankManagementProject.mapper;

import com.ikucuk.BankManagementProject.dto.AccountDto;
import com.ikucuk.BankManagementProject.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account,AccountDto accountDto){
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setBranchAddress(accountDto.getBranchAddress());
        return accountDto;
    }

    public static Account mapToAccountEntity(AccountDto accountDto, Account account){
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setBranchAddress(accountDto.getBranchAddress());
        return account;
    }


}
