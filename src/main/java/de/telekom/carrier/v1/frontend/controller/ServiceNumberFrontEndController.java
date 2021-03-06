package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.ServiceNumber;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.ServiceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

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

    @GetMapping(path = "/addServiceNumberWithCarrierId/{carrierId}")
    public String showFormByCarrier(Model model, @PathVariable("carrierId") Long carrierId) {
        model.addAttribute("serviceNumber", new ServiceNumber());
        model.addAttribute("carriers", carrierService.findById(carrierId).get());
        return "add-serviceNumberBackCarrier";    }


    @PostMapping(value = "/addServiceNumberWithCarrierId")
    public String submitFormByCarrier(@ModelAttribute ServiceNumber serviceNumber,Model model) {
        try {
            model.addAttribute("serviceNumber",serviceNumber);
            serviceNumber.setCreateDate(new Date());
            serviceNumberService.save(serviceNumber);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/findByIdCarrier/" + serviceNumber.getCarrier().getId();
    }

    @GetMapping(path = "/addServiceNumber")
    public String showForm(Model model) {
        model.addAttribute("serviceNumber", new ServiceNumber());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-serviceNumber";
    }

    //https://www.baeldung.com/thymeleaf-list
    @PostMapping(value = "/addServiceNumber")
    public String submitForm(@ModelAttribute ServiceNumber serviceNumber,Model model) {
        try {
            model.addAttribute("serviceNumber",serviceNumber);
            serviceNumber.setCreateDate(new Date());
            serviceNumberService.save(serviceNumber);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/serviceNumbersFindAll";
    }

    @GetMapping(value = "/deleteServiceNumber/{serviceNumberId}")
    public String delete(@PathVariable Long serviceNumberId) {
        serviceNumberService.deleteById(serviceNumberId);
        return "redirect:/serviceNumbersFindAll";
    }

    @GetMapping(value = "/deleteServiceNumberByCarrier/{serviceNumberId}")
    public String deleteByCarrier(@PathVariable Long serviceNumberId) {
        ServiceNumber serviceNumberRequest = serviceNumberService.findById(serviceNumberId).get();
        serviceNumberService.deleteById(serviceNumberId);
        return "redirect:/findByIdCarrier/" + serviceNumberRequest.getCarrier().getId();
    }

    @GetMapping(path = "/editServiceNumber/{serviceNumberId}")
    public String showEditForm(@PathVariable(name = "serviceNumberId") Long serviceNumberId, Model model) {
        ServiceNumber serviceNumber = new ServiceNumber();
        try {
            serviceNumber = serviceNumberService.findById(serviceNumberId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+serviceNumberId));
            model.addAttribute("serviceNumber", serviceNumber);
            model.addAttribute("carriers", carrierService.findById(serviceNumber.getCarrier().getId()).get());
            return "edit-serviceNumber";
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
            serviceNumber.setUpdateDate(new Date());
            serviceNumberService.update(serviceNumber);
            return "redirect:/serviceNumbersFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping(path = "/editServiceNumberByCarrier/{serviceNumberId}")
    public String showEditFormByCarrier(@PathVariable(name = "serviceNumberId") Long serviceNumberId, Model model) {
        ServiceNumber serviceNumber = new ServiceNumber();
        try {
            serviceNumber = serviceNumberService.findById(serviceNumberId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+serviceNumberId));
            model.addAttribute("serviceNumber", serviceNumber);
            model.addAttribute("carriers", carrierService.findById(serviceNumber.getCarrier().getId()).get());
            return "edit-serviceNumberBackCarrier";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + serviceNumber);
            return "error";
        }
    }

    @PostMapping(value = "/updateServiceNumberByCarrier/{serviceNumberId}")
    public String updateServiceNumberByCarrier(@PathVariable(name = "serviceNumberId") Long serviceNumberId,
                                      @ModelAttribute("serviceNumber") ServiceNumber serviceNumber,
                                      Model model) {
        try {
            serviceNumber.setId(serviceNumberId);
            serviceNumber.setUpdateDate(new Date());
            serviceNumberService.update(serviceNumber);
            return "redirect:/findByIdCarrier/" + serviceNumber.getCarrier().getId();
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
}
