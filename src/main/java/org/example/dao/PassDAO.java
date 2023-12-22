package org.example.dao;

import org.example.entities.Pass;
import org.example.entities.SellerType;
import org.example.entities.Ticket;
import org.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PassDAO {
   private EntityManager en;

   public PassDAO(EntityManager en){
       this.en=en;
   }

   public void save(Pass abbonamento){
       EntityTransaction ent= en.getTransaction();
       ent.begin();
       en.persist(abbonamento);
       ent.commit();
       System.out.println("abbonamento registrato con successo");
   }

    public Pass findById(UUID uuid) {
        TypedQuery<Pass> query = en.createQuery("SELECT r FROM Pass r WHERE r.uuid = :id", Pass.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        en.remove(this.findById(uuid));
    }

    public List<Pass> getNumberPassBySellerType (SellerType sellerType){
        TypedQuery<Pass> getPassBySellerType = en.createQuery("getNumberPassBySellerType", Pass.class);
        getPassBySellerType.setParameter("sellerType", sellerType);
        return getPassBySellerType.getResultList();
    }

    public Pass getPass (UUID card) {
        TypedQuery<Pass> getPassByCard = en.createQuery("SELECT a FROM Pass a WHERE a.user.card = :cardNumber", Pass.class);
        getPassByCard.setParameter("cardNumber", card);
        return getPassByCard.getSingleResult();
    }

    public boolean checkValidityPassByCardNumber (UUID card) {
        TypedQuery<Pass> getPassByCard = en.createQuery("SELECT a FROM Pass a WHERE a.user.card = :cardNumber", Pass.class);
        getPassByCard.setParameter("cardNumber", card);
        Pass passes = getPassByCard.getSingleResult();
        if (passes != null) {
            LocalDate currentDate = LocalDate.now();

            if (passes.getDataDiScadenza().isBefore(currentDate)) {
                System.out.println("La tessera è scaduta.");
                return false;
            } else {
                System.out.println("La tessera è valida.");
                return true;
            }
        } else {
            System.out.println("Tessera non trovata.");
            return false;
        }
    }

    public void addPassToUser (User user, Pass pass){
       EntityTransaction transaction = en.getTransaction();
       transaction.begin();
        Query addPass = en.createQuery("UPDATE Pass p SET p.user = :user WHERE p = :pass");
        addPass.setParameter("user", user);
        addPass.setParameter("pass", pass);
        addPass.executeUpdate();
        transaction.commit();
    }
}
