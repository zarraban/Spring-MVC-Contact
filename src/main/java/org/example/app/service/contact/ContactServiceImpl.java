package org.example.app.service.contact;

import org.example.app.dto.RequestContact;
import org.example.app.entity.Contact;
import org.example.app.repository.contact.ContactRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service("contactService")
public class ContactServiceImpl implements ContactService {


    private final ContactRepository repository;


    public ContactServiceImpl(@Qualifier("contactRepository") ContactRepository contactRepository){
        this.repository = contactRepository;

    }

    @Transactional
    @Override
    public void create(RequestContact request) {
        repository.create(request);
    }
    @Transactional(readOnly = true)
    @Override
    public List<Contact> readAll() {
        return repository.readAll().orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Contact readById(Long id) {
        return repository.readById(id).orElse(null);
    }

    @Transactional
    @Override
    public boolean updateById(Long id, RequestContact request) {
        return repository.updateById(id,request);
    }

    @Transactional
    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Contact readLast() {
        return repository.readLast().orElse(null);
    }
}
