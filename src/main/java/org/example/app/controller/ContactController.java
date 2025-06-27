package org.example.app.controller;

import org.example.app.dto.RequestContact;
import org.example.app.entity.Contact;
import org.example.app.service.ai.AiService;
import org.example.app.service.contact.ContactService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ContactController {

    private final ContactService service;
    private final AiService aiService;

    public ContactController(@Qualifier("contactService")ContactService service, @Qualifier("aiService") AiService aiService){
        this.service = service;
        this.aiService = aiService;
    }

    @GetMapping("/contacts")
    public String fetchAllContacts(Model model){
        List<Contact> list = service.readAll();
        model.addAttribute("title", "Contacts");
        if(list.isEmpty()){
            model.addAttribute("contactInfo", "No contacts added yet :(");
        }else {
            model.addAttribute("contacts", list);

        }
        model.addAttribute("fragmentName","contact-list");

        return "layout";
    }

    @PostMapping("/contacts")
    public String fetchAllContactsAndGetName(@RequestParam("greetName") String name, Model model){
        List<Contact> list = service.readAll();
        if(name == null || name.trim().isEmpty()){
            model.addAttribute("greetText","Please enter name!");
        }
        else {
            String aiResponse = aiService.callAiWithPrompt("generate short greeting(about 10< words) for the guy or girl with name " + name.trim()+
                    " write only one greeting and nothing else. But this greeting should be every time unique");
            model.addAttribute("greetText",aiResponse);
        }

        model.addAttribute("title", "Contacts");
        if(list.isEmpty()){
            model.addAttribute("contactInfo", "No contacts added yet :(");
        }else {
            model.addAttribute("contacts", list);

        }
        model.addAttribute("fragmentName","contact-list");

        return "layout";
    }

    @GetMapping("/create-contact")
    public String createContact(Model model){
        model.addAttribute("title", "Add contact");
        model.addAttribute("fragmentName","contact-add");

        return "layout";
    }

    @PostMapping("/add-contact")
    public RedirectView addUser(@ModelAttribute RequestContact requestContact){
        RedirectView redirectView = new RedirectView("./contacts");
        service.create(requestContact);
        return redirectView;

    }

    @RequestMapping("/update-contact/{id}")
    public String updateContact(@PathVariable("id")Long id, Model model){
        model.addAttribute("title", "Update contact");
        Contact contact = service.readById(id);
        model.addAttribute("contact", contact);
        model.addAttribute("fragmentName", "contact-update");
        return "layout";
    }

    @PostMapping("/change-contact")
    public RedirectView changeContact(@ModelAttribute RequestContact requestContact){
        RedirectView redirectView = new RedirectView("./contacts");
        service.updateById(requestContact.id(),requestContact);
        return redirectView;

    }
    @GetMapping("/delete-contact/{id}")
    public RedirectView deleteContact(@PathVariable("id")Long id){
        RedirectView redirectView = new RedirectView("../contacts");
        service.deleteById(id);
        return redirectView;
    }

}
