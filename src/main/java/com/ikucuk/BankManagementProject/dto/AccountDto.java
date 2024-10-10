package com.ikucuk.BankManagementProject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotEmpty(message = "Account number cannot be a null or empty!")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Account number should be 10 digits!")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be a null or empty!")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be a null or empty!")
    private String branchAddress;

}
