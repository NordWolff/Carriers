package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.Address;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.PztConfig;
import de.telekom.carrier.v1.api.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/carrier")
@CrossOrigin(origins = "http://localhost:4200")
public class CarrierRestController {

    @Autowired
    CarrierService carrierService;

    @Autowired
    ServiceNumberService serviceNumberService;

    @Autowired
    AdditionalAgreementService additionalAgreementService;

    @Autowired
    CustomerAddressService customerAddressService;

    @Autowired
    UsageAgreementService usageAgreementService;

    @Autowired
    PztConfigService pztConfigService;

    @GetMapping(path = "/findAll")
    public ResponseEntity findAll() {
        return new ResponseEntity(carrierService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/findByCustomerNumber/{customerNumber}")
    public ResponseEntity findByCustomerNumber(@PathVariable(value = "customerNumber") String customerNumber){
        Carrier carrier;
       if(customerNumber.length()==10) {
           carrier = carrierService.findByCustomerNumber(customerNumber).orElseThrow(() -> new IllegalArgumentException("Sorry, not found Carrier by CustomerNumber:" + customerNumber + "."));
           return new ResponseEntity(carrier, HttpStatus.FOUND);
       } else {
           return new ResponseEntity("Sorry, CustomerNumber:" + customerNumber + " not valid", HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody Carrier carrier) {
        carrierService.save(carrier);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(path = "/update")
    public ResponseEntity update(@RequestBody Carrier carrier) {
        carrierService.update(carrier);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Delete a Carrier by ID",
            produces = "application/json",
            response = Carrier.class
    )
    @DeleteMapping(path = "/delete/{carrierId}")
    public void deleteCarrier(@PathVariable Long carrierId) {
        carrierService.findById(carrierId).orElseThrow( () -> new IllegalArgumentException("Sorry, not found Carrier by ID:" + carrierId));
        carrierService.deleteById(carrierId);
    }
}
