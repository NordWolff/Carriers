package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.AdditionalAgreement;
import de.telekom.carrier.v1.api.entity.Carrier;
import de.telekom.carrier.v1.api.entity.CustomerAddress;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/")
public class CustomerAddressFrontEndController {

    @Autowired
    CustomerAddressService customerAddressService;

    @Autowired
    CarrierService carrierService;


    @GetMapping(path = "/customerAddressesFindAll")
    public ModelAndView findAll(){
        ModelAndView view = new ModelAndView("all-customerAddresses");
        view.addObject("addresses", customerAddressService.findAll());
        return view;
    }

    @GetMapping(path = "/addCustomerAddress")
    public String showForm(Model model) {
        model.addAttribute("address", new CustomerAddress());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-customerAddress";
    }

    @PostMapping(value = "/addCustomerAddress")
    public String submitForm(@ModelAttribute(value = "address")  CustomerAddress customerAddress, Model model) {
        try {
            customerAddress.setCreateDate(new Date());
            model.addAttribute("address",customerAddress);
            customerAddressService.save(customerAddress);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "view-customerAddress";
    }

    @GetMapping(value = "/deleteCustomerAddress/{customerId}")
    public String delete(@PathVariable Long customerId) {
        customerAddressService.deleteById(customerId);
        return "redirect:/customerAddressesFindAll";
    }

    /**
     * Edit bzw. Update Methode
     * @param customerId
     * @return
     */

    @GetMapping(value = "/editCustomerAddress/{customerId}")
    public String showEdit(@PathVariable Long customerId, Model model) {
        CustomerAddress customerAddress = new CustomerAddress();
        try {
            customerAddress = customerAddressService.findById(customerId).orElseThrow(() -> new IllegalArgumentException("Not found CustomerAddress ID:"+customerId));
            model.addAttribute("address", customerAddress);
            model.addAttribute("carriers", carrierService.findAll());
            return "edit-customerAddress";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found CustomerAddress ID:" + customerId);
            return "error";
        }
    }

    @PostMapping(value = "/updateCustomerAddress/{customerId}")
    public String updateCarrier(@PathVariable Long customerId,
                                @ModelAttribute("address") CustomerAddress customerAddress,
                                Model model) {
        try {
            customerAddress.setId(customerId);
            customerAddressService.update(customerAddress);
            return "redirect:/customerAddressesFindAll/";
        } catch (Exception exception) { // log exception first,
            // then show error
            String errorMessage = exception.getMessage();
            //logger.error(errorMessage);
            model.addAttribute("error", errorMessage);
            return "error";
        }
    }
}
