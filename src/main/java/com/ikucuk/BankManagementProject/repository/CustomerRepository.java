package com.ikucuk.BankManagementProject.repository;

import com.ikucuk.BankManagementProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
