package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.OslAgreement;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.OslAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/oslAgreements")
public class OslAgreementRestController {

    @Autowired
    CarrierService carrierService;

    @Autowired
    OslAgreementService oslAgreementService;

    @GetMapping(path = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OslAgreement>> findAll(){
        return ResponseEntity.ok(oslAgreementService.findAll());
    }

    @PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OslAgreement> create(@RequestBody OslAgreement oslAgreement){
        OslAgreement createdOslAgreement = oslAgreementService.save(oslAgreement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOslAgreement);
    }

    @PostMapping(value = "/addUsedHardware")
    public ResponseEntity<OslAgreement> createUsedHardware(@RequestBody OslAgreement oslAgreement,@RequestParam String hardware) {
        oslAgreement.addUsedHardware(hardware.toString());
        oslAgreementService.update(oslAgreement);
        return ResponseEntity.ok(oslAgreement);
    }
}
