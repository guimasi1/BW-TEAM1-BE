package org.example.dao;

import org.example.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TicketDAO {
    private EntityManager ent;

    public TicketDAO(EntityManager ent){
        this.ent=ent;
    }

    public void save(Ticket biglietto){
        EntityTransaction en=ent.getTransaction();
        en.begin();
        ent.persist(biglietto);
        en.commit();
        System.out.println("biglietto confermato");
    }
}
