package org.example.dao;

import org.example.entities.Route;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class UserDAO {
    private final EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUser(User user){
        EntityTransaction transiction = em.getTransaction();
        transiction.begin();
        em.persist(user);
        transiction.commit();
        System.out.println("User saved");
    }

    public User findUserByID(UUID card){
        User found = em.find(User.class, card);
        return found;
    }

    public void deleteUserById(UUID card){
        User found = this.findUserByID(card);
        if(found != null){
            EntityTransaction transiction = em.getTransaction();
            transiction.begin();
            em.remove(found);
            transiction.commit();
            System.out.println("L'utente con numero tessera " + card + "è stato eliminato correttamente");
        }
    }

    public List<User> getAllUsers () {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    public User getUserByNameAndSurname(String name, String surname) {
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.name = :name AND u.surname = :surname", User.class);
        query.setParameter("name", name);
        query.setParameter("surname", surname);

        return query.getSingleResult();
    }
}
