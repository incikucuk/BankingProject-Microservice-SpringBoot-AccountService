package com.ikucuk.BankManagementProject.repository;

import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //metod yazımı java pojo nesnesidir yani Customer ile aynı olmalıdır.
    Optional<Customer> findByMobileNumber(String mobileNumber);

}
