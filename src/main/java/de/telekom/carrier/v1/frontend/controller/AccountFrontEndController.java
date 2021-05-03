package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.Account;
import de.telekom.carrier.v1.api.service.AccountService;
import de.telekom.carrier.v1.api.service.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(path = "/")
public class AccountFrontEndController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CarrierService carrierService;

    @GetMapping(path = "/accountsFindAll")
    private ModelAndView findAll() {
        boolean disabledButton=false;
        ModelAndView view = new ModelAndView("all-accounts");
        view.addObject("accounts", accountService.findAll());
        if(accountService.findAll().size() == carrierService.findAll().size()){
            disabledButton = true;
        }
        view.addObject("disabledButton",disabledButton);
        return view;
    }

    /**
     * with CarrierId
     * and Return Back findByCarrierId
     * @param model
     * @return
     */

    @GetMapping(path = "/addAccountWithCarrierId/{carrierId}")
    public String showFormByCarrierId(Model model,@PathVariable("carrierId")Long carrierId) {
        model.addAttribute("account", new Account());
        model.addAttribute("carriers", carrierService.findById(carrierId).get());
        return "add-accountAgreementBackCarrier";
    }

    @PostMapping(value = "/addAccountWithCarrierId")
    public String submitFormByCarrierId(@ModelAttribute(value = "account")  Account account, Model model) {
        try {
            model.addAttribute("account",account);
            account.setCreateDate(new Date());
            accountService.save(account);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/findByIdCarrier/" + account.getCarrier().getId();
    }

    /**
     *
     * @param model
     * @return
     */

    @GetMapping(path = "/addAccount")
    public String showForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-Account";
    }

    @PostMapping(value = "/addAccount")
    public String submitForm(@ModelAttribute(value = "account")  Account account, Model model) {
        try {
            model.addAttribute("account",account);
            account.setCreateDate(new Date());
            accountService.save(account);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/accountsFindAll";
    }

    /**
     *
     * @param accountsId
     * @param model
     * @return
     */
    @GetMapping(path = "/editAccount/{accountsId}")
    public String showEditFormRedirectCarrier(@PathVariable(name = "accountsId") Long accountsId, Model model) {
        Account account;
        try {
            account = accountService.findById(accountsId).orElseThrow(() -> new IllegalArgumentException("Not found Account ID:"+accountsId));
            model.addAttribute("account", account);
            model.addAttribute("carriers", carrierService.findById(account.getCarrier().getId()).get());
            return "edit-accountBackCarrier";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Account ID:" + accountsId);
            return "error";
        }
    }

    /**
     *
     * @param accountsId
     * @param model
     * @return
     */
    @GetMapping(path = "/editAccount/{accountsId}")
    public String showEditFormRedirectAll(@PathVariable(name = "accountsId") Long accountsId, Model model) {
        Account account;
        try {
            account = accountService.findById(accountsId).orElseThrow(() -> new IllegalArgumentException("Not found Account ID:"+accountsId));
            model.addAttribute("account", account);
            model.addAttribute("carriers", carrierService.findById(account.getCarrier().getId()).get());
            return "edit-account";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Account ID:" + accountsId);
            return "error";
        }
    }
    @PostMapping(value = "/updateAccountRedirectCarrier/{accountsId}")
    public String updateRedirektCarrier(@PathVariable(name = "accountsId") Long accountsId,
                         @ModelAttribute("account") Account account,
                         Model model
    ) {
        try {
            account.setId(accountsId);
            account.setUpdateDate(new Date());
            accountService.update(account);
                return "redirect:/findByIdCarrier/" + account.getCarrier().getId();
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @PostMapping(value = "/updateAccount/{accountsId}")
    public String updateRedirectFindAll(@PathVariable(name = "accountsId") Long accountsId,
                         @ModelAttribute("account") Account account,
                         Model model
                         ) {
        try {
            account.setId(accountsId);
            account.setUpdateDate(new Date());
            accountService.update(account);
                return "redirect:/accountsFindAll";

        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
    @GetMapping(value = "/deleteAccount/{accountsId}")
    public String delete(@PathVariable Long accountsId) {
        accountService.deleteById(accountsId);
        return "redirect:/accountsFindAll";
    }
}
