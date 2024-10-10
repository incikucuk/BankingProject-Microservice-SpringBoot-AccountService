package com.ikucuk.BankManagementProject.controller;

import com.ikucuk.BankManagementProject.constants.AccountConstants;
import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.dto.ResponseDto;
import com.ikucuk.BankManagementProject.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//produces={MediaType.APPLICATION_JSON_VALUE)} => restapi'de geri donus tipi veri formatının ne oldugunu belirtmek icin kullanılır.

@RestController
@RequestMapping(path = "/api/accounts", produces={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;  //@Autowired kullanımı gerek yoktur çünkü @AllArgsConstructor(burada parametresiz kullanacagin func. varsa @Autowired kullanıma gerek yoktur.Parametreliyse kullanilmalidir.) kullanıldı.

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));

    }

    //phoneNumber gore account bilgilerini getir
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone number should be 10 digits!") String phoneNumber){
        //@RequestBody tanımlılarda dto nesnesi kullanılmalı bu yuzden validasyon yaparken @valid,@RequestParam gibi tek value gececek olanlarda @valid yerine@Pattern gibi spasifik validasyon antt.kullanılmalı
       CustomerDto customerDto =iAccountService.fetchAccount(phoneNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone number should be 10 digits!") String phoneNumber){
        boolean isDeleted = iAccountService.deleteAccount(phoneNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(AccountConstants.STATUS_500,AccountConstants.MESSAGE_500));
        }
    }
  
}
