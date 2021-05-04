package de.telekom.carrier.v1.frontend.controller;

import de.telekom.carrier.v1.api.entity.Contact;
import de.telekom.carrier.v1.api.service.CarrierService;
import de.telekom.carrier.v1.api.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(path = "/")
public class ContactFrontEndController {

    @Autowired
    ContactService contactService;

    @Autowired
    CarrierService carrierService;

    @GetMapping(path = "/contactsFindAll")
    public ModelAndView findALl(){
        boolean disabledButton=false;
        ModelAndView view = new ModelAndView("all-contacts");
        view.addObject("contacts",contactService.findAll());
        if(contactService.findAll().size() == carrierService.findAll().size()){
            disabledButton = true;
        }
        view.addObject("disabledButton",disabledButton);

        return view;
    }

    @GetMapping(path = "/addContactByCarrier/{carrierId}")
    public String showFormByCarrier(Model model, @PathVariable("carrierId")Long carrierId) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("carriers", carrierService.findById(carrierId).get());
        return "add-contactBackCarrier";
    }

    @PostMapping(value = "/addContactByCarrier")
    public String submitFormByCarrier(@ModelAttribute(value = "contact") Contact contact, Model model) {
        try {
            model.addAttribute("contact",contact);
            contact.setCreateDate(new Date());
            contactService.save(contact);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/findByIdCarrier/" + contact.getCarrier().getId();
    }

    @GetMapping(path = "/addContact")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact());
        model.addAttribute("carriers", carrierService.findAllByOrderByNameAsc());
        return "add-contact";
    }

    @PostMapping(value = "/addContact")
    public String submitForm(@ModelAttribute(value = "contact") Contact contact, Model model) {
        try {
            model.addAttribute("contact",contact);
            contact.setCreateDate(new Date());
            contactService.save(contact);
        } catch (Exception exception) {
            model.addAttribute("error",exception.getMessage());
            return "error";
        }
        return "redirect:/contactsFindAll";
    }

    @GetMapping(path = "/editContactWithContactId/{contactId}")
    public String showEditFormByCarrier(@PathVariable(name = "contactId") Long contactId, Model model) {
        Contact contact;
        try {
            contact = contactService.findById(contactId).orElseThrow(() -> new IllegalArgumentException("Not found Contact ID:"+contactId));
            model.addAttribute("contact", contact);
            model.addAttribute("carriers", carrierService.findById(contact.getCarrier().getId()).get());
            return "edit-contactBackCarrier";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Contact ID:" + contactId);
            return "error";
        }
    }

    @PostMapping(value = "/updateContactWithContactId/{contactId}")
    public String updateByCarrier(@PathVariable(name = "contactId") Long contactId,
                         @ModelAttribute("contact") Contact contact) {
            contact.setId(contactId);
            contact.setUpdateDate(new Date());
            contactService.update(contact);
            return "redirect:/findByIdCarrier/" + contact.getCarrier().getId();
    }

    @GetMapping(path = "/editContact/{contactId}")
    public String showEditForm(@PathVariable(name = "contactId") Long contactId, Model model) {
        Contact contact = new Contact();
        try {
            contact = contactService.findById(contactId).orElseThrow(() -> new IllegalArgumentException("Not found Contact ID:"+contactId));
            model.addAttribute("contact", contact);
            model.addAttribute("carriers", carrierService.findById(contact.getCarrier().getId()).get());
            return "edit-contact";
        } catch(IllegalArgumentException illegalArgumentException) {
            model.addAttribute("error", "Not found Contact ID:" + contactId);
            return "error";
        }
    }

    @PostMapping(value = "/updateContact/{contactId}")
    public String update(@PathVariable(name = "contactId") Long contactId,
                         @ModelAttribute("contact") Contact contact,
                         Model model) {
        try {
            contact.setId(contactId);
            contact.setUpdateDate(new Date());
            contactService.update(contact);
            return "redirect:/contactsFindAll";
        } catch (Exception exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
    @GetMapping(value = "/deleteContact/{contactId}")
    public String delete(@PathVariable Long contactId) {
        contactService.deleteById(contactId);
        return "redirect:/contactsFindAll";
    }
}
