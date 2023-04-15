package org.learning.fotoalbum.repository;

import org.learning.fotoalbum.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    
}
