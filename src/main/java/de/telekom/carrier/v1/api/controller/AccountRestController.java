package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.Account;
import de.telekom.carrier.v1.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @DeleteMapping(path = "/deletebyAccountId/{accountId}")
    public ResponseEntity<Account> deleteByAccountId(@PathVariable(name = "accountId")Long accountId ){
        accountService.deleteById(accountId);
        return ResponseEntity.ok().build();
    }
}
