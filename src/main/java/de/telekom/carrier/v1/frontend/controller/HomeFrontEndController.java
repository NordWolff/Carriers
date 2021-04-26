package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.service.CarrierService;
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

    @GetMapping(path = {"/index.html",""})
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("index");
        return view;
    }
}
