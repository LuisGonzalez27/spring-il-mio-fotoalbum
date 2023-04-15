package org.learning.fotoalbum.service;

import org.learning.fotoalbum.model.Contact;
import org.learning.fotoalbum.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    public Contact getById(Integer id) {
        Optional<Contact> result = contactRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException(Integer.toString(id));
        }
    }

    public Contact createContact(Contact formContact) {
        Contact contactToPersist = new Contact();

        contactToPersist.setEmail(formContact.getEmail());
        contactToPersist.setMessage(formContact.getMessage());

        return contactRepository.save(contactToPersist);
    }
}
