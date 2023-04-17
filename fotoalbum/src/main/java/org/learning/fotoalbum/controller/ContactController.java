package org.learning.fotoalbum.controller;


import org.learning.fotoalbum.model.Contact;
import org.learning.fotoalbum.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public String index(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("list", contacts);
        return "/contacts/index";
    }
}
