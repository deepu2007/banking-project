package com.easy.bankingApp.contoller;

import com.easy.bankingApp.dto.AccountDto;
import com.easy.bankingApp.entity.Account;
import com.easy.bankingApp.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;


@RestController
@RequestMapping("api/accounts")
public class AccountController  {

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    private AccountService accountService;

    //Create Account
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        System.out.println("testing");
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto =accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){

         double amount =request.get("amount");
         AccountDto accountDto = accountService.deposit(id,amount);
         return   ResponseEntity.ok(accountDto);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
        double amount =request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return   ResponseEntity.ok(accountDto);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        List<AccountDto> accountDtoList=accountService.getAllAccounts();
        return ResponseEntity.ok(accountDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
          accountService.deleteAccount(id);
        return   ResponseEntity.ok("Account deleted successfully");
    }

    @PutMapping("/{id}/newwithdraw")
    public ResponseEntity<AccountDto> newwithdraw(@PathVariable Long id, @RequestBody Map<String , Double> request){
        double amount =request.get("amount");
        AccountDto accountDto =accountService.newwithdraw(id,amount);
        return   ResponseEntity.ok(accountDto);
    }

    }
