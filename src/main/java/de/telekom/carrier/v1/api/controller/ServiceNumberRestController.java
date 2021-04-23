package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.service.ServiceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/serviceNumber")
public class ServiceNumberRestController {

    @Autowired
    ServiceNumberService serviceNumberService;

    @GetMapping(path = "/findAll")
    public ResponseEntity findAll() {
        return new ResponseEntity(serviceNumberService.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody ServiceNumber serviceNumber) {
        serviceNumberService.save(serviceNumber);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
