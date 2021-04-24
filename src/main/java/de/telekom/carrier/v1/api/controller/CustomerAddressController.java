package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.CustomerAddress;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/customerAddresses")
public class CustomerAddressController {

    @Autowired
    CarrierService carrierService;

    @Autowired
    CustomerAddressService customerAddressService;

    @GetMapping(path = "/findAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-customerAddresses");
        view.addObject("addresses", customerAddressService.findAll());
        return view;
    }
}
