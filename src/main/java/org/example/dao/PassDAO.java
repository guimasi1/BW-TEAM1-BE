package org.example.dao;

import org.example.entities.Pass;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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

}
