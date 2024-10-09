package com.ikucuk.BankManagementProject.service;

import com.ikucuk.BankManagementProject.dto.CustomerDto;

public interface IAccountService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String phoneNumber);
}
