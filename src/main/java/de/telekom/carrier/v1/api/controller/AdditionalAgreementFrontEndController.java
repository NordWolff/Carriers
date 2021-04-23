package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.service.AdditionalAgreementService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/additionalAgreements")
public class AdditionalAgreementFrontEndController {
    @Autowired
    AdditionalAgreementService additionalAgreementService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/findAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-additionalAgreements");
        view.addObject("additionalAgreements", additionalAgreementService.findAll());
        return view;
    }

    @GetMapping(path = "/addAgreement")
    public String showForm(Model model) {
        model.addAttribute("additionalAgreement", new AdditionalAgreement());
        model.addAttribute("carriers", carrierService.findAll());
        return "add-additionalAgreement";
    }
}
