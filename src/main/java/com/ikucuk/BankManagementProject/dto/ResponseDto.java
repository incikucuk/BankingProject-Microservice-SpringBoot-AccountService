package com.ikucuk.BankManagementProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

    private String message;
    private String statusMessage;
}
