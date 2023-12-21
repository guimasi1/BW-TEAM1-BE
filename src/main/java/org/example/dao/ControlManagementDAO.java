package org.example.dao;

import org.example.entities.ControlManagement;
import org.example.entities.SellerType;
import org.example.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ControlManagementDAO {
    private EntityManager em;

    public ControlManagementDAO(EntityManager em) {
        this.em = em;
    }

    public void save(ControlManagement controlManagement) {
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        em.persist(controlManagement);

        entityTransaction.commit();

        System.out.println("sistema avviato");

    }


    public ControlManagement findById(UUID uuid) {
        TypedQuery<ControlManagement> query = em.createQuery("SELECT c FROM ControlManagement c WHERE c.uuid = :id", ControlManagement.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        em.remove(this.findById(uuid));
    }


    public List<Ticket> ticketTracking(LocalDate inizio, LocalDate fine) {
        TypedQuery<Ticket> query = em.createQuery("SELECT t FROM Ticket t WHERE t.dataEmissione >= :inizio AND  t.dataEmissione <= :fine", Ticket.class);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);
        return query.getResultList();
    }

    public Long numberTicket(LocalDate inizio, LocalDate fine) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM Ticket t WHERE t.dataEmissione >= :inizio AND  t.dataEmissione <= :fine", Long.class);
        query.setParameter("inizio", inizio);
        query.setParameter("fine", fine);

        return query.getSingleResult();

    }

//    public List<Ticket> getTicketSeller(SellerType sellerType){
//        TypedQuery<Ticket> getTicket = em.createQuery("SELECT t FROM Ticket t WHERE t.seller.sellerType = :sellerType", Ticket.class);
//        getTicket.setParameter("sellerType", sellerType);
//        return getTicket.getResultList();
//    }
}


