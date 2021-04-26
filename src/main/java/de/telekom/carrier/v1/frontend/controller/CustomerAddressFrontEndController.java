package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CustomerAddressFrontEndController {

    @Autowired
    CustomerAddressService customerAddressService;

    @Autowired
    CarrierService carrierService;


    @GetMapping(path = "/customerAddressesFindAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-customerAddresses");
        view.addObject("addresses", customerAddressService.findAll());
        return view;
    }
}
