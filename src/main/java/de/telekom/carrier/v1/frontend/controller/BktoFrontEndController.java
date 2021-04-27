package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.Bkto;
import de.telekom.carrier.v1.api.service.BktoService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(path = "/")
public class BktoFrontEndController {

    @Autowired
    private BktoService bktoService;

    @Autowired
    private CarrierService carrierService;

    @GetMapping(path = "/bktosFindAll")
    private ModelAndView findAll() {
        ModelAndView view = new ModelAndView("all-bktos");
        view.addObject("bktos", bktoService.findAll());
        return view;
    }

    @GetMapping(path = "/addBkto")
    public String showForm(Model model) {
        model.addAttribute("account", new Bkto());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-bkto";
    }

    @PostMapping(value = "/addBkto")
    public String submitForm(@ModelAttribute(value = "account")  Bkto bkto, Model model) {
        try {
            model.addAttribute("account",bkto);
            bkto.setCreateDate(new Date());
            bktoService.save(bkto);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "view-bkto";
    }

    @GetMapping(path = "/editBkto/{accountsId}")
    public String showEditForm(@PathVariable(name = "accountsId") Long accountsId, Model model) {
        Bkto bkto = new Bkto();
        try {
            bkto = bktoService.findById(accountsId).orElseThrow(() -> new IllegalArgumentException("Not found Bkto ID:"+accountsId));
            model.addAttribute("account", bkto);
            model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
            return "edit-bkto";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Bkto ID:" + accountsId);
            return "error";
        }
    }

    @PostMapping(value = "/updateBkto/{accountsId}")
    public String update(@PathVariable(name = "accountsId") Long accountsId,
                         @ModelAttribute("serviceNumber") Bkto bkto,
                         Model model) {
        try {
            bkto.setId(accountsId);
            bkto.setUpdateDate(new Date());
            bktoService.update(bkto);
            return "redirect:/bktosFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
    @GetMapping(value = "/deleteBkto/{accountsId}")
    public String delete(@PathVariable Long accountsId) {
        bktoService.deleteById(accountsId);
        return "redirect:/bktosFindAll";
    }
}
