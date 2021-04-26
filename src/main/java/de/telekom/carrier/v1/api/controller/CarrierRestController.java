package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.Address;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.PztConfig;
import de.telekom.carrier.v1.api.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/carriers",consumes = {"text/plain", "application/*"})
@CrossOrigin(origins = "http://localhost:4200")
public class CarrierRestController {

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/findAll",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Carrier>> findAll() {
        return ResponseEntity.ok(carrierService.findAll());
    }

    @GetMapping(path = "/findByCustomerNumber/{customerNumber}")
    public ResponseEntity<Carrier> findByCustomerNumber(@PathVariable(value = "customerNumber") String customerNumber){
        Carrier carrier;
       if(customerNumber.length()==10) {
           carrier = carrierService.findByCustomerNumber(customerNumber).orElseThrow(() -> new IllegalArgumentException("Sorry, not found Carrier by CustomerNumber:" + customerNumber + "."));
           return ResponseEntity.ok(carrier);
       } else {
           return new ResponseEntity("Sorry, CustomerNumber:" + customerNumber + " not valid", HttpStatus.NOT_FOUND);
       }

    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carrier> create(@RequestBody Carrier carrier) {
        Carrier carrierDb = carrierService.save(carrier);
        return ResponseEntity.ok(carrierDb);
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Carrier carrier, @PathVariable("id") Long id) {
        carrier.setId(id);
        Carrier carrierDb = carrierService.update(carrier);
        return ResponseEntity.ok(carrierDb);
    }

    @ApiOperation(
            value = "Delete a Carrier by ID",
            produces = "application/json",
            response = Carrier.class
    )
    @DeleteMapping(path = "/delete/{carrierId}")
    public ResponseEntity<Carrier> deleteCarrier(@PathVariable Long carrierId) {
        Optional<Carrier> carrierDb = null;
        carrierDb =  carrierService.findById(carrierId);
        if(carrierDb.isPresent()) {
            carrierService.deleteById(carrierId);
            return ResponseEntity.ok(carrierDb.get());
        } else {
            carrierDb.orElseThrow( () -> new IllegalArgumentException("Sorry, not found Carrier by ID:" + carrierId));
            return ResponseEntity.noContent().build();
        }
    }
}
