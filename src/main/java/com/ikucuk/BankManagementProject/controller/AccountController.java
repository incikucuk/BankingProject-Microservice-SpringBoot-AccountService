package com.ikucuk.BankManagementProject.controller;

import com.ikucuk.BankManagementProject.constants.AccountConstants;
import com.ikucuk.BankManagementProject.dto.CustomerDto;
import com.ikucuk.BankManagementProject.dto.ErrorResponseDto;
import com.ikucuk.BankManagementProject.dto.ResponseDto;
import com.ikucuk.BankManagementProject.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//produces={MediaType.APPLICATION_JSON_VALUE)} => restapi'de geri donus tipi veri formatının ne oldugunu belirtmek icin kullanılır.
@Tag(
        name = "CRUD REST API's for Account in Banking System",
        description = "CRUD REST API's  in Banking Project to CREATE,UPDATE,DELETE account details"
)
@RestController
@RequestMapping(path = "/api/accounts", produces={MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountController {

    private IAccountService iAccountService;  //@Autowired kullanımı gerek yoktur çünkü @AllArgsConstructor(burada parametresiz kullanacagin func. varsa @Autowired kullanıma gerek yoktur.Parametreliyse kullanilmalidir.) kullanıldı.

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & Account inside Banking System"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountConstants.STATUS_201,AccountConstants.MESSAGE_201));

    }
    @Operation(
            summary = "Fetch Account REST API",
            description = "REST API to create Fetch Customer & Account details based on mobile number inside Banking System"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    //phoneNumber gore account bilgilerini getir
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                               @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone number should be 10 digits!") String phoneNumber){
        //@RequestBody tanımlılarda dto nesnesi kullanılmalı bu yuzden validasyon yaparken @valid,@RequestParam gibi tek value gececek olanlarda @valid yerine@Pattern gibi spasifik validasyon antt.kullanılmalı
       CustomerDto customerDto =iAccountService.fetchAccount(phoneNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account REST API",
            description = "REST API to Update Customer & Account inside Banking System"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP Status EXPECTATION FAILED"
            ),
            @ApiResponse(
                    //ApiResponse dosyası icinde GlobalException classını okuyamadigi icin tanimlayamz ve bu kisimda dökümada ui kısmında hata gorunuyor(500'de succes mesajı vermesi gibi)Bunu duzeltmek icin soyle;
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    ) //ErrorResponseDto icinde tanimlanan semayi izleyerek ErrorResponse gondermis ve 500 hata durumunda dogru messa verebilecegiz ui kismina
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Account REST API",
            description = "REST API to Delete Customer & Account inside Banking System"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "EXPECTATION FAILED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                                @Pattern(regexp = "(^$|[0-9]{10})",message = "Phone number should be 10 digits!") String phoneNumber){
        boolean isDeleted = iAccountService.deleteAccount(phoneNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(AccountConstants.STATUS_200,AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(AccountConstants.STATUS_417,AccountConstants.MESSAGE_417_DELETE));
        }
    }
  
}
