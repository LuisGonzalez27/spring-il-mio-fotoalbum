package org.learning.fotoalbum.service;

import org.learning.fotoalbum.model.Contact;
import org.learning.fotoalbum.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAll() {
        return contactRepository.findAll(Sort.by("email"));
    }

    public Contact createContact(Contact formContact) {
        Contact contactToPersist = new Contact();

        contactToPersist.setEmail(formContact.getEmail());
        contactToPersist.setMessage(formContact.getMessage());

        return contactRepository.save(contactToPersist);
    }

    public Contact getById(Integer id) {
        return contactRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
