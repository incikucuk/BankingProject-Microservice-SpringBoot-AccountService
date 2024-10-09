package com.ikucuk.BankManagementProject.service.impl;

import com.ikucuk.BankManagementProject.constants.AccountConstants;
import com.ikucuk.BankManagementProject.dto.AccountDto;
import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.entity.Account;
import com.ikucuk.BankManagementProject.entity.Customer;
import com.ikucuk.BankManagementProject.exception.CustomerAlreadyExistsException;
import com.ikucuk.BankManagementProject.exception.ResourceNotFoundException;
import com.ikucuk.BankManagementProject.mapper.AccountMapper;
import com.ikucuk.BankManagementProject.mapper.CustomerMapper;
import com.ikucuk.BankManagementProject.repository.AccountRepository;
import com.ikucuk.BankManagementProject.repository.CustomerRepository;
import com.ikucuk.BankManagementProject.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import static com.ikucuk.BankManagementProject.mapper.AccountMapper.mapToAccountEntity;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomerEntity(customerDto, new Customer());

        //Req1:db yeni kayit olustururken aynı phoneNumber giris yapilamaz
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());

        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile number!"
                    +customer.getMobileNumber());
        }
        customer.setCreatedAt(LocalDateTime.now());   //Kayit işleminde BaseEntityden de gelen, sadece create değerleri burada set edilmeli
        customer.setCreatedBy("Anonymous");

        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Customer","mobileNumber",mobileNumber));

        //Req2:customer'ın account bilgilerini getirmek istiyoruz.(Hem customer hem de account bilgilerini getirmek istiyoruz)
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString()));

            Account accounts = AccountMapper.mapToAccountEntity(accountDto, account);
            Account savedAccount = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            if(customerId != null){
                Customer customer = customerRepository.findById(customerId).orElseThrow(
                        () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
                );
                Customer customers = CustomerMapper.mapToCustomerEntity(customerDto, customer);
                Customer savedCustomer = customerRepository.save(customers);
                isUpdated = true;
            }
        }
        return isUpdated;
    }

    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();

        newAccount.setCustomerId(customer.getCustomerId());

        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccountNumber);

        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return accountRepository.save(newAccount);
    }

    //Req3:kullanici hesap numarası hariç diğer tüm bilgileri güncelleyebilmelidir.


}
