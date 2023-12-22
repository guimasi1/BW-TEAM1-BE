package org.example.dao;

import org.example.entities.Seller;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class SellerDAO {
    private final EntityManager em;

    public SellerDAO(EntityManager em) {
        this.em = em;
    }

    public void saveSeller(Seller seller){
        EntityTransaction transiction = em.getTransaction();
        transiction.begin();
        em.persist(seller);
        transiction.commit();
    }

    public Seller findSellerByID(UUID id){
        Seller found = em.find(Seller.class, id);
        return found;
    }

    public void deleteSellerById(UUID id){
        Seller found = this.findSellerByID(id);
        if(found != null){
            EntityTransaction transiction = em.getTransaction();
            transiction.begin();
            em.remove(found);
            transiction.commit();
            System.out.println("Il rivenditore con ID " + id + "è stato eliminato correttamente");
        }
    }

    public List<Seller> getAllSellers () {
        TypedQuery<Seller> query = em.createQuery("SELECT r FROM Seller r", Seller.class);
        return query.getResultList();
    }
}


