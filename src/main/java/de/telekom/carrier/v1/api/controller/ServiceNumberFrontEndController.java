package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.ServiceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class ServiceNumberFrontEndController {

    @Autowired
    ServiceNumberService serviceNumberService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/serviceNumbersFindAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("all-serviceNumbers");
        view.addObject("serviceNumbers",serviceNumberService.findAll());
        return view;
    }

    @GetMapping(path = "/addServiceNumber")
    public String showForm(Model model) {
        model.addAttribute("serviceNumber", new ServiceNumber());
        model.addAttribute("carriers", carrierService.findAll());
        return "add-serviceNumber";
    }

    //https://www.baeldung.com/thymeleaf-list
    @PostMapping(value = "/addServiceNumber")
    public String submitForm(@ModelAttribute ServiceNumber serviceNumber,Model model) {
        try {
            model.addAttribute("serviceNumber",serviceNumber);
            serviceNumberService.save(serviceNumber);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "serviceNumberView";
    }

    @GetMapping(value = "/deleteServiceNumber/{serviceNumberId}")
    public String delete(@PathVariable Long serviceNumberId) {
        serviceNumberService.deleteById(serviceNumberId);
        return "redirect:/serviceNumbersFindAll";
    }

    @GetMapping(path = "/editServiceNumber/{serviceNumberId}")
    public String showEditForm(@PathVariable(name = "serviceNumberId") Long serviceNumberId, Model model) {
        ServiceNumber serviceNumber = new ServiceNumber();
        try {
            serviceNumber = serviceNumberService.findById(serviceNumberId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+serviceNumberId));
            model.addAttribute("serviceNumber", serviceNumber);
            model.addAttribute("carriers", carrierService.findAll());
            return "update-serviceNumber";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + serviceNumber);
            return "error";
        }
    }

    @PostMapping(value = "/updateServiceNumber/{serviceNumberId}")
    public String updateServiceNumber(@PathVariable(name = "serviceNumberId") Long serviceNumberId,
                                @ModelAttribute("serviceNumber") ServiceNumber serviceNumber,
                                Model model) {
        try {
            serviceNumber.setId(serviceNumberId);
            serviceNumberService.update(serviceNumber);
            return "redirect:/serviceNumbersFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
}
