package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.Bkto;
import de.telekom.carrier.v1.api.service.BktoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class BktoRestController {

    @Autowired
    private BktoService bktoService;

    @DeleteMapping(path = "/deletebyAccountId/{accountId}")
    public ResponseEntity<Bkto> deleteByAccountId(@PathVariable(name = "accountId")Long accountId ){
        bktoService.deleteById(accountId);
        return ResponseEntity.ok().build();
    }
}
