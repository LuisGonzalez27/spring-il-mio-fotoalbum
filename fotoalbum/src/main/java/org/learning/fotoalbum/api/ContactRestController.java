package org.learning.fotoalbum.api;

import org.learning.fotoalbum.exceptions.PhotoNotFoundException;
import org.learning.fotoalbum.model.Contact;
import org.learning.fotoalbum.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("api/v1/contacts")
public class ContactRestController {
    @Autowired
    private ContactService contactService;

    @GetMapping("/{id}")
    public Contact getById(@PathVariable Integer id) {
        try {
            return contactService.getById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Contact getContactList(@RequestBody Contact contact) {
        return contactService.createContact(contact);
    }

}
