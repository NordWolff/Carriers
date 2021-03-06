package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.service.AdditionalAgreementService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class AdditionalAgreementFrontEndController {
    @Autowired
    AdditionalAgreementService additionalAgreementService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/additionalAgreementsFindAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-additionalAgreements");
        view.addObject("additionalAgreements", additionalAgreementService.findAll());
        return view;
    }

    @GetMapping(path = "/addAdditionalAgreement")
    public String showForm(Model model) {
        model.addAttribute("agreement", new AdditionalAgreement());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-additionalAgreement";
    }

    @PostMapping(value = "/addAdditionalAgreement")
    public String submitForm(@ModelAttribute(value = "agreement")  AdditionalAgreement additionalAgreement, Model model) {
        try {
            model.addAttribute("agreement",additionalAgreement);
            additionalAgreementService.save(additionalAgreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/additionalAgreementsFindAll";
    }

    /**
     * AddByCarrierID
     * @param model
     * @return
     */
    @GetMapping(path = "/addAdditionalAgreementWithCarrier/{carrierId}")
    public String showFormByCarrierId(Model model, @PathVariable("carrierId")Long carrierId) {
        model.addAttribute("agreement", new AdditionalAgreement());
        model.addAttribute("carriers", carrierService.findById(carrierId).get());
        return "add-additionalAgreementBackCarrier";
    }

    /**
     * AddByCarrierID
     * @param model
     * @return
     */

    @PostMapping(value = "/addAdditionalAgreementWithCarrier")
    public String submitFormByCarrierId(@ModelAttribute(value = "agreement") AdditionalAgreement additionalAgreement, Model model) {
        try {
            model.addAttribute("agreement",additionalAgreement);
            additionalAgreementService.save(additionalAgreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/findByIdCarrier/"+additionalAgreement.getCarrier().getId();
    }


    @GetMapping(path = "/editAdditionalAgreement/{agreementsId}")
    public String showEditForm(@PathVariable(name = "agreementsId") Long agreementsId, Model model) {
        AdditionalAgreement additionalAgreement = new AdditionalAgreement();
        try {
            additionalAgreement = additionalAgreementService.findById(agreementsId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+agreementsId));
            model.addAttribute("agreement", additionalAgreement);
            model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
            return "edit-additionalAgreement";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + agreementsId);
            return "error";
        }
    }

    @PostMapping(value = "/updateAdditionalAgreement/{agreementsId}")
    public String update(@PathVariable(name = "agreementsId") Long agreementsId,
                         @ModelAttribute("serviceNumber") AdditionalAgreement additionalAgreement,
                         Model model) {
        try {
            additionalAgreement.setId(agreementsId);
            additionalAgreementService.update(additionalAgreement);
            return "redirect:/additionalAgreementsFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/deleteAdditionalAgreement/{agreementsId}")
    public String delete(@PathVariable Long agreementsId) {
        additionalAgreementService.deleteById(agreementsId);
        return "redirect:/additionalAgreementsFindAll";
    }
}
