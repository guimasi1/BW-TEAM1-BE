package org.example.dao;

import org.example.entities.ControlManagement;
import org.example.entities.Pass;
import org.example.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class ControlManagementDAO {
    private EntityManager ent;
    public ControlManagementDAO(EntityManager ent){
       this.ent=ent;
    }

    public void save(ControlManagement controlManagement){
        EntityTransaction entityTransaction=ent.getTransaction();

        entityTransaction.begin();

        ent.persist(controlManagement);

        entityTransaction.commit();

        System.out.println("sistema avviato");

    }


    public ControlManagement findById(UUID uuid) {
        TypedQuery<ControlManagement> query = ent.createQuery("SELECT r FROM Route r WHERE r.uuid = :id", ControlManagement.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        ent.remove(this.findById(uuid));
    }



    public List<Ticket> ticketTracking(){
        
    }


}
