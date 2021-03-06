package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.UsageAgreement;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.UsageAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class UsageAgreementFrontEndController {

    @Autowired
    UsageAgreementService usageAgreementService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/usageAgreementsFindAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-usageAgreements");
        view.addObject("usageAgreements",usageAgreementService.findAll());
        return view;
    }

    /**
     * with findByCarrierId
     * and Back findByCarrierId
     * @param model
     * @return
     */

    @GetMapping(path = "/addUsageAgreementWithCarrierId/{carrierId}")
    public String showFormByCarrierId(Model model,@PathVariable("carrierId") Long carrierId) {
        model.addAttribute("agreement", new UsageAgreement());
        model.addAttribute("carriers", carrierService.findById(carrierId).get());
        return "add-usageAgreementBackCarrier";
    }

    @PostMapping(value = "/addUsageAgreementWithCarrierId")
    public String submitFormByCarrierId(@ModelAttribute(value = "agreement") UsageAgreement usageAgreement, Model model) {
        try {
            model.addAttribute("agreement",usageAgreement);
            usageAgreementService.save(usageAgreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/findByIdCarrier/"+usageAgreement.getCarrier().getId();
    }

    @GetMapping(path = "/addUsageAgreement")
    public String showForm(Model model) {
        model.addAttribute("agreement", new UsageAgreement());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-usageAgreement";
    }

    @PostMapping(value = "/addUsageAgreement")
    public String submitForm(@ModelAttribute(value = "agreement") UsageAgreement usageAgreement, Model model) {
        try {
            model.addAttribute("agreement",usageAgreement);
            usageAgreementService.save(usageAgreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/usageAgreementsFindAll";
    }


    @GetMapping(path = "/editUsageAgreement/{agreementsId}")
    public String showEditForm(@PathVariable(name = "agreementsId") Long agreementsId, Model model) {
        UsageAgreement usageAgreement = new UsageAgreement();
        try {
            usageAgreement = usageAgreementService.findById(agreementsId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+agreementsId));
            model.addAttribute("agreement", usageAgreement);
            model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
            return "edit-usageAgreement";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + agreementsId);
            return "error";
        }
    }

    @PostMapping(value = "/updateUsageAgreement/{agreementsId}")
    public String update(@PathVariable(name = "agreementsId") Long agreementsId,
                                      @ModelAttribute("serviceNumber") UsageAgreement usageAgreement,
                                      Model model) {
        try {
            usageAgreement.setId(agreementsId);
            usageAgreementService.update(usageAgreement);
            return "redirect:/usageAgreementsFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/deleteUsageAgreement/{agreementsId}")
    public String delete(@PathVariable Long agreementsId) {
        usageAgreementService.deleteById(agreementsId);
        return "redirect:/usageAgreementsFindAll";
    }
}
