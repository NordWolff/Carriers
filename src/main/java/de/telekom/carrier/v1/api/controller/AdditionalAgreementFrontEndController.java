package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.entity.UsageAgreement;
import de.telekom.carrier.v1.api.service.AdditionalAgreementService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("agreement", new AdditionalAgreement());
        model.addAttribute("carriers", carrierService.findAll());
        return "add-additionalAgreement";
    }

    @PostMapping(value = "/addAgreement")
    public String submitForm(@ModelAttribute(value = "agreement")  AdditionalAgreement additionalAgreement, Model model) {
        try {
            model.addAttribute("agreement",additionalAgreement);
            additionalAgreementService.save(additionalAgreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "additionalAgreementView";
    }


    @GetMapping(path = "/edit/{agreementsId}")
    public String showEditForm(@PathVariable(name = "agreementsId") Long agreementsId, Model model) {
        AdditionalAgreement additionalAgreement = new AdditionalAgreement();
        try {
            additionalAgreement = additionalAgreementService.findById(agreementsId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+agreementsId));
            model.addAttribute("agreement", additionalAgreement);
            model.addAttribute("carriers", carrierService.findAll());
            return "update-additionalAgreement";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + agreementsId);
            return "error";
        }
    }

    @PostMapping(value = "/update/{agreementsId}")
    public String update(@PathVariable(name = "agreementsId") Long agreementsId,
                         @ModelAttribute("serviceNumber") AdditionalAgreement additionalAgreement,
                         Model model) {
        try {
            additionalAgreement.setId(agreementsId);
            additionalAgreementService.update(additionalAgreement);
            return "redirect:/additionalAgreements/findAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/delete/{agreementsId}")
    public String delete(@PathVariable Long agreementsId) {
        additionalAgreementService.deleteById(agreementsId);
        return "redirect:/additionalAgreements/findAll";
    }
}
