package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeFrontEndController {

    @Autowired
    CarrierService carrierService;

    @Autowired
    AdditionalAgreementService additionalAgreementService;

    @Autowired
    UsageAgreementService usageAgreementService;

    @Autowired
    ServiceNumberService serviceNumberService;

    @Autowired
    CustomerAddressService customerAddressService;

    @Autowired
    OslAgreementService oslAgreementService;

    @Autowired
    ContactService contactService;

    @Autowired
    AccountService accountService;

    @GetMapping(path = {"/index.html",""})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        view.addObject("carriers", carrierService.findAll());
        view.addObject("additionalAgreements", additionalAgreementService.findAll());
        view.addObject("usageAgreements", usageAgreementService.findAll());
        view.addObject("serviceNumbers", serviceNumberService.findAll());
        view.addObject("customerAddresses", customerAddressService.findAll());
        return view;
    }
}
