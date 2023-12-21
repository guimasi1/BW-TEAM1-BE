package org.example.dao;

import org.example.entities.Seller;
import org.example.entities.SellerType;
import org.example.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

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

    public Ticket findById(UUID uuid) {
        TypedQuery<Ticket> query = ent.createQuery("SELECT r FROM Route r WHERE r.uuid = :id", Ticket.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        ent.remove(this.findById(uuid));
    }

    public List<Ticket> getNumberTicketsBySellerType (SellerType sellerType){
        TypedQuery<Ticket> getTicketsBySellerType = ent.createNamedQuery("getNumberTicketsBySellerType", Ticket.class);
        getTicketsBySellerType.setParameter("sellerType", sellerType);
        return getTicketsBySellerType.getResultList();
    }

    public List<Ticket> getAllTrips() {
        TypedQuery<Ticket> query = ent.createQuery("SELECT t FROM Ticket t", Ticket.class);
        return query.getResultList();
    }

    public List<Ticket> getTicketSeller(SellerType sellerType){
        TypedQuery<Ticket> getTicket = ent.createQuery("SELECT t FROM Ticket t JOIN Seller s WHERE t.seller.sellerType = :sellerType", Ticket.class);
        getTicket.setParameter("sellerType", sellerType);
        return getTicket.getResultList();
    }

}
