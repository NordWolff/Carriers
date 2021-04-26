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

    @GetMapping(path = "/carrierFindAll")
    public ModelAndView findAll() {
        ModelAndView view = new ModelAndView("all-carriers");
        view.addObject("carriers",carrierService.findAll());
        return view;
    }

    @GetMapping(path = "/findByIdCarrier/{carrierId}")
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

    /**
     * Edit bzw. Update Methode
     * @param carrierId
     * @return
     */

    @GetMapping(value = "/editCarrier/{carrierId}")
    public String showEdit(@PathVariable Long carrierId, Model model) {
        Carrier carrier = new Carrier();
        try {
            carrier = carrierService.findById(carrierId).orElseThrow(() -> new IllegalArgumentException("Not found Carrier ID:"+carrierId));
            model.addAttribute("carrier", carrier);
            return "edit-carrier";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Carrier ID:" + carrierId);
            return "error";
        }
    }

    @PostMapping(value = "/updateCarrier/{carrierId}")
    public String updateCarrier(@PathVariable Long carrierId,
                                @ModelAttribute("carrier") Carrier carrier,
                                Model model) {
        try {
            carrier.setId(carrierId);
            carrierService.update(carrier);
            return "redirect:/carrierFindAll/";
        } catch (Exception exception) { // log exception first,
            // then show error
            String errorMessage = exception.getMessage();
            //logger.error(errorMessage);
            model.addAttribute("error", errorMessage);
            return "all-carriers";
        }
    }

    @GetMapping(value = "/deleteCarrier/{carrierId}")
    public String delete(@PathVariable Long carrierId) {
        carrierService.deleteById(carrierId);
        return "redirect:/carrierFindAll";
    }

}
