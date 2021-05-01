package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.dto.Cluster;
import de.telekom.carrier.v1.api.dto.Hardware;
import de.telekom.carrier.v1.api.dto.OslAgreementCreationDto;
import de.telekom.carrier.v1.api.entity.*;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.OslAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping(path = "/")
public class OslAgreementFrontEndController {

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

    @GetMapping(path = "/addOslAgreement")
    public String showForm(Model model) {
        model.addAttribute("agreement", new OslAgreement());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-oslAgreement";
    }

    @PostMapping(value = "/addOslAgreement")
    public String submitForm(@ModelAttribute(value = "agreement") OslAgreement agreement, Model model) {
        try {
            model.addAttribute("agreement",agreement);
            agreement.setCreateDate(new Date());
            oslAgreementService.save(agreement);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/oslAgreementsFindAll";
    }

    @GetMapping(path = "/editOslAgreement/{agreementsId}")
    public String showEditForm(@PathVariable(name = "agreementsId") Long agreementsId, Model model) {
        OslAgreement oslAgreement = new OslAgreement();
        try {
            oslAgreement = oslAgreementService.findById(agreementsId).orElseThrow(() -> new IllegalArgumentException("Not found Osl ID:"+agreementsId));
            model.addAttribute("agreement", oslAgreement);
            model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
            return "edit-oslAgreement";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Osl ID:" + agreementsId);
            return "error";
        }
    }

    @PostMapping(value = "/updateOslAgreement/{agreementsId}")
    public String update(@PathVariable(name = "agreementsId") Long agreementsId,
                         @ModelAttribute("agreement") OslAgreement oslAgreement,
                         Model model) {
        try {
            oslAgreement.setId(agreementsId);
            oslAgreementService.update(oslAgreement);
            return "redirect:/oslAgreementsFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping(value = "/deleteOslAgreement/{agreementsId}")
    public String delete(@PathVariable Long agreementsId) {
        oslAgreementService.deleteById(agreementsId);
        return "redirect:/oslAgreementsFindAll";
    }

    @GetMapping(path = "/findByIdOslAgreement/{oslId}")
    public ModelAndView findById(@PathVariable("oslId") Long oslId) {
        ModelAndView view = new ModelAndView();
        try {
            OslAgreement oslAgreement = oslAgreementService.findById(oslId).orElseThrow(() -> new IllegalArgumentException("Not found Osl ID:" + oslId));
            view.setViewName("findById-oslAgreement");
            view.addObject("agreement",oslAgreement);
            view.setStatus(HttpStatus.FOUND);
        } catch (IllegalArgumentException illegalArgumentException) {
            view.setViewName("error");
            view.addObject("error","Not found Osl ID:" + oslId);
            view.setStatus(HttpStatus.NOT_FOUND);
        }
        return view;
    }

    @GetMapping(path = "/addUsedHardware")
    public String showFormUsedHardwareWithOutOslId(Model model) {
        OslAgreementCreationDto hardware = new OslAgreementCreationDto();
        hardware.addUsedHardware(new Hardware());
        model.addAttribute("hardware", hardware);
        model.addAttribute("agreements", oslAgreementService.findAll());
        return "add-usedHardware";
    }

    @GetMapping(path = "/addUsedHardware/{id}")
    public String showFormUsedHardwareWithOslId(Model model,@PathVariable("id") Long id) {
        OslAgreementCreationDto hardware = new OslAgreementCreationDto();
            hardware.addUsedHardware(new Hardware());
        model.addAttribute("hardware", hardware);
        model.addAttribute("agreements", oslAgreementService.findById(id).get());
        return "add-usedHardware";
    }

    @PostMapping(value = "/addUsedHardware")
    public String submitFormUsedHardware(@ModelAttribute(value = "agreement") OslAgreement agreement,@ModelAttribute(value = "hardware") OslAgreementCreationDto hardware, Model model) {
        model.addAttribute("hardware",hardware);
        model.addAttribute("agreement",agreement);
        hardware.getHardwareList().forEach(element -> agreement.addUsedHardware(element.getName()));
            oslAgreementService.update(agreement);
        return "redirect:/findByIdOslAgreement/"+agreement.getId();
    }

    /**
     * DSL CLUSTER
     */

    @GetMapping(path = "/addClusterDsl")
    public String showFormTalClusterWithOutOslId(Model model) {
        model.addAttribute("isClusterDsl",true);
        model.addAttribute("clusters", new Cluster());
        model.addAttribute("agreements", oslAgreementService.findAll());
        return "add-clusterForOsl";
    }

    @GetMapping(path = "/addClusterDsl/{id}")
    public String showFormTalClusterWithOslId(Model model,@PathVariable("id") Long id) {
        model.addAttribute("isClusterDsl",true);
        model.addAttribute("clusters", new Cluster());
        model.addAttribute("agreements", oslAgreementService.findById(id).get());
        return "add-clusterForOsl";
    }

    @PostMapping(value = "/addClusterDsl")
    public String submitFormDslCluster(@ModelAttribute(value = "agreement") OslAgreement agreement,@ModelAttribute(value = "cluster") Cluster cluster, Model model) {
        model.addAttribute("cluster",cluster);
        model.addAttribute("agreement",agreement);
        agreement.addClusterDsl(cluster.getCluster());
        oslAgreementService.update(agreement);
        return "redirect:/findByIdOslAgreement/"+agreement.getId();
    }

    /**
     * TAL CLUSTER
     */

    @GetMapping(path = "/addClusterTal")
    public String showFormClusterTalWithOutOslId(Model model) {
        model.addAttribute("isClusterDsl",false);
        model.addAttribute("clusters", new Cluster());
        model.addAttribute("agreements", oslAgreementService.findAll());
        return "add-clusterForOsl";
    }

    @GetMapping(path = "/addClusterTal/{id}")
    public String showFormClusterTalWithOslId(Model model,@PathVariable("id") Long id) {
        model.addAttribute("isClusterDsl",false);
        model.addAttribute("clusters", new Cluster());
        model.addAttribute("agreements", oslAgreementService.findById(id).get());
        return "add-clusterForOsl";
    }

    @PostMapping(value = "/addClusterTal")
    public String submitFormTalCluster(@ModelAttribute(value = "agreement") OslAgreement agreement,@ModelAttribute(value = "cluster") Cluster cluster, Model model) {
        model.addAttribute("cluster",cluster);
        model.addAttribute("agreement",agreement);
        agreement.addClusterTal(cluster.getCluster());
        oslAgreementService.update(agreement);
        return "redirect:/findByIdOslAgreement/"+agreement.getId();
    }





}
