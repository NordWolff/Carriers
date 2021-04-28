package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.OslAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class OslAgreementController {

    @Autowired
    OslAgreementService oslAgreementService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "oslAgreementsFindAll")
    public ModelAndView findALl(){
        boolean disabledButton=false;
        ModelAndView view = new ModelAndView("all-oslAgreements");
        view.addObject("agreements", oslAgreementService.findAll());
        if(oslAgreementService.findAll().size() == carrierService.findAll().size()){
            disabledButton = true;
        }
        view.addObject("disabledButton",disabledButton);
        return view;
    }
}
