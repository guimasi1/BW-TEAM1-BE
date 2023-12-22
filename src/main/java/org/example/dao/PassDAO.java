package org.example.dao;

import org.example.entities.Pass;
import org.example.entities.SellerType;
import org.example.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
        TypedQuery<Pass> query = en.createQuery("SELECT r FROM Route r WHERE r.uuid = :id", Pass.class);
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

    public boolean checkValidityPassByCardNumber (UUID card) {
        TypedQuery<Pass> getPassByCard = en.createQuery("SELECT a FROM Pass a WHERE a.user.card = :cardNumber", Pass.class);
        getPassByCard.setParameter("cardNumber", card);
        List<Pass> passes = getPassByCard.getResultList();
        if (!passes.isEmpty()) {
            Pass pass = passes.get(0);
            LocalDate currentDate = LocalDate.now();

            if (pass.getDataDiScadenza().isBefore(currentDate)) {
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
}
