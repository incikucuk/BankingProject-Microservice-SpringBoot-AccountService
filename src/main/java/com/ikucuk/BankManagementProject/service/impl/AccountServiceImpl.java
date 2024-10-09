package com.ikucuk.BankManagementProject.service.impl;

import com.ikucuk.BankManagementProject.constants.AccountConstants;
import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.entity.Account;
import com.ikucuk.BankManagementProject.entity.Customer;
import com.ikucuk.BankManagementProject.mapper.CustomerMapper;
import com.ikucuk.BankManagementProject.repository.AccountRepository;
import com.ikucuk.BankManagementProject.repository.CustomerRepository;
import com.ikucuk.BankManagementProject.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomerEntity(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);

        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return accountRepository.save(newAccount);
    }
}
