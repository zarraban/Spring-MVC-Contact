package org.example.app.repository.contact;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.example.app.dto.RequestContact;
import org.example.app.entity.Contact;

import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {


    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(RequestContact request) {
        entityManager.persist(request);
    }

    @Override
    public Optional<List<Contact>> readAll() {
        String sql = " SELECT c FROM Contact c";
        return Optional.of(entityManager.createQuery(sql, Contact.class).getResultList());
    }

    @Override
    public Optional<Contact> readById(Long id) {
        return Optional.of(entityManager.find(Contact.class, id));
    }

    @Override
    public boolean updateById(Long id, RequestContact request) {
        String hql = "UPDATE Contact c SET c.name =: name, c.phone = :phone, c.surname=:surname WHERE c.id=:id";
        Query query = entityManager.createQuery(hql, Contact.class);
        query.setParameter("id",id);
        query.setParameter("name", request.name());
        query.setParameter("surname", request.surname());
        query.setParameter("phone",request.phone());
        return query.executeUpdate()>0;
    }

    @Override
    public boolean deleteById(Long id) {
        String hql = "DELETE FROM Contact c WHERE c.id=:id";
        Query query = entityManager.createQuery(hql, Contact.class);
        query.setParameter("id",id);
        return query.executeUpdate() > 0;
    }

    @Override
    public Optional<Contact> readLast() {
        String hql = "select c from Contact c order by c.id desc";
        Query query = entityManager.createQuery(hql, Contact.class);
        query.setMaxResults(1);
        return Optional.of((Contact) query.getSingleResult());
    }
}
