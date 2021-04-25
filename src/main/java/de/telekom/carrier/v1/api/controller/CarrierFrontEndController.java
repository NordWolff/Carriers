package de.telekom.carrier.v1.api.controller;

import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/")
public class CarrierFrontEndController {

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = {"/index.html",""})
    public ModelAndView index() {
       ModelAndView view = new ModelAndView("index");
       return view;
    }

    @GetMapping(path = "/findAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("all-carriers");
        view.addObject("carriers",carrierService.findAll());
        return view;
    }

    @GetMapping(path = "/findByCarrierId/{carrierId}")
    public ModelAndView findById(@PathVariable("carrierId") Long carrierId) {
        ModelAndView view = new ModelAndView();
        try {
            Carrier carrier = carrierService.findById(carrierId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:" + carrierId));
            view.setViewName("findById-carrier");
            view.addObject("carrier",carrier);
            view.setStatus(HttpStatus.FOUND);
        } catch (IllegalArgumentException illegalArgumentException) {
            view.setViewName("error");
            view.addObject("error","Not found Carrier ID:" + carrierId);
            view.setStatus(HttpStatus.NOT_FOUND);
        }
        return view;
    }

    @GetMapping(path = "/addCarrier")
    public String showForm(Model model) {
        model.addAttribute("carrier", new Carrier());
        return "add-carrier";
    }

    //https://www.baeldung.com/thymeleaf-list
    @PostMapping(value = "/addCarrier")
    public String submitForm(@ModelAttribute Carrier carrier, Model model) {
        try {
            model.addAttribute("carrier", carrier);
            carrierService.save(carrier);
        } catch (Exception exception) {
            model.addAttribute("error","Duplicate entry '"+ carrier.getCustomerNumber() +"' for key");
            return "error";
        }
        return "view-carrier";
    }

    @GetMapping(value = "/delete/{carrierId}")
    public String delete(@PathVariable Long carrierId) {
        carrierService.deleteById(carrierId);
        return "redirect:/findAll";
    }

}
