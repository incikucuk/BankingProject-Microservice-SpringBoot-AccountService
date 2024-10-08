package com.ikucuk.BankManagementProject.service.impl;

import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.repository.AccountRepository;
import com.ikucuk.BankManagementProject.repository.CustomerRepository;
import com.ikucuk.BankManagementProject.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
