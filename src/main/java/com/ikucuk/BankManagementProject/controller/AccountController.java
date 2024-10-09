package com.ikucuk.BankManagementProject.controller;

import com.ikucuk.BankManagementProject.constants.AccountConstants;
import com.ikucuk.BankManagementProject.dto.AccountDto;
import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.dto.ResponseDto;
import com.ikucuk.BankManagementProject.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//produces={MediaType.APPLICATION_JSON_VALUE)} => restapi'de geri donus tipi veri formatının ne oldugunu belirtmek icin kullanılır.

@RestController
@RequestMapping(path = "/api/accounts", produces={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountController {

    private IAccountService iAccountService;  //@Autowired kullanımı gerek yoktur çünkü @AllArgsConstructor(burada parametresiz kullanacagin func. varsa @Autowired kullanıma gerek yoktur.Parametreliyse kullanilmalidir.) kullanıldı.

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));

    }
  
}
