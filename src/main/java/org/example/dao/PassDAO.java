package org.example.dao;

import org.example.entities.Pass;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
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
        TypedQuery<Pass> query = en.createQuery("SELECT p FROM Pass p WHERE p.uuid = :id", Pass.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        en.remove(this.findById(uuid));
    }

}
