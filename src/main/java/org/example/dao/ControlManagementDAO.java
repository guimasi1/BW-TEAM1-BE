package org.example.dao;

import org.example.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ControlManagementDAO {
    private EntityManager em;
    public ControlManagementDAO(EntityManager em){
        this.em=em;
    }

    public void save(ControlManagement controlManagement){
        EntityTransaction entityTransaction=em.getTransaction();

        entityTransaction.begin();

        em.persist(controlManagement);

        entityTransaction.commit();

        System.out.println("Elemento acquistato.");

    }


    public ControlManagement findById(UUID uuid) {
        TypedQuery<ControlManagement> query = em.createQuery("SELECT c FROM ControlManagement c WHERE c.uuid = :id", ControlManagement.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public Pass findPassByIDTEST (UUID uuid){
        return em.find(Pass.class, uuid);
    }

    public Ticket findTicketById(UUID uuid) {
        TypedQuery<Ticket> query = em.createQuery("SELECT c FROM Ticket c WHERE c.uuid = :id", Ticket.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public void removeById(UUID uuid) {
        em.remove(this.findById(uuid));
    }



    public List<Ticket> ticketTracking(LocalDate inizio , LocalDate fine){
        TypedQuery<Ticket> query= em.createQuery("SELECT t FROM Ticket t WHERE t.dataEmissione >= :inizio AND  t.dataEmissione <= :fine",Ticket.class);
        query.setParameter("inizio",inizio);
        query.setParameter("fine",fine);
        return query.getResultList();
    }

    public Long numberTicket(LocalDate inizio,LocalDate fine){
        TypedQuery<Long> query= em.createQuery("SELECT COUNT(t) FROM Ticket t WHERE t.dataEmissione >= :inizio AND  t.dataEmissione <= :fine",Long.class);
        query.setParameter("inizio",inizio);
        query.setParameter("fine",fine);

        return query.getSingleResult();

    }


    public List<Pass> passTracking(LocalDate inizio , LocalDate fine){
        TypedQuery<Pass> query= em.createQuery("SELECT p FROM Pass p WHERE p.dataEmissione >= :inizio AND  p.dataEmissione <= :fine",Pass.class);
        query.setParameter("inizio",inizio);
        query.setParameter("fine",fine);
        return query.getResultList();
    }

    public Long numberPass(LocalDate inizio,LocalDate fine){
        TypedQuery<Long> query= em.createQuery("SELECT COUNT(p) FROM Pass p WHERE p.dataEmissione >= :inizio AND  p.dataEmissione <= :fine",Long.class);
        query.setParameter("inizio",inizio);
        query.setParameter("fine",fine);

        return query.getSingleResult();

    }

    public List<Pass> getAllPass () {
        TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p", Pass.class);
        return query.getResultList();
    }

    public void validateTicket (Ticket ticket, LocalDate today,Vehicle vehicle) {
        if(ticket.getDataDiVidimazione() == null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Query modify = em.createQuery("UPDATE Ticket t SET t.dataDiVidimazione = :today WHERE t = :ticket");
            // Query modifyVehicleList = em.createQuery("UPDATE Ticket t SET t.vehicles = :vehicle WHERE t = :ticket");
            modify.setParameter("today", LocalDate.now());
            modify.setParameter("ticket", ticket);
            modify.executeUpdate();
            List<Vehicle> vehiclesToAdd = new ArrayList<>();
            ticket.setVehicles(vehiclesToAdd);
            ticket.getVehicles().add(vehicle);

            System.out.println("Biglietto vidimato.");
            transaction.commit();
        } else System.out.println("Impossibile vidimare il biglietto poiché già utilizzato.");
    }

    public List<Ticket> getTicketsByVehicleAndPeriod(Vehicle vehicle, LocalDate startPeriod, LocalDate endPeriod) {
        TypedQuery<Ticket> query = em.createQuery(
                "SELECT t FROM Ticket t JOIN t.vehicles v WHERE v = :vehicle AND" +
                        " t.dataDiVidimazione >= :startPeriod AND t.dataDiVidimazione <= :endPeriod",
                Ticket.class
        );
        query.setParameter("vehicle", vehicle);
        query.setParameter("startPeriod", startPeriod);
        query.setParameter("endPeriod", endPeriod);

        return query.getResultList();
    }

    public List<Ticket> getTicketsByUser (User user) {
        TypedQuery<Ticket> query = em.createQuery(
                "SELECT t FROM Ticket t JOIN t.user u WHERE u = :user",
                Ticket.class
        );
        query.setParameter("user", user);

        return query.getResultList();
    }
        public List<Ticket> getTicketSeller(SellerType sellerType){
        TypedQuery<Ticket> getTicket = em.createQuery("SELECT t FROM Ticket t WHERE t.seller.sellerType = :sellerType", Ticket.class);
        getTicket.setParameter("sellerType", sellerType);
        return getTicket.getResultList();
    }
    public List<Ticket> getTicketsBySeller(Seller seller){
        TypedQuery<Ticket> getTicket = em.createQuery("SELECT t FROM Ticket t WHERE t.seller = :seller", Ticket.class);
        getTicket.setParameter("seller", seller);
        return getTicket.getResultList();
    }

    public Pass getPassByUser (User user) {
        TypedQuery<Pass> getPass = em.createQuery("SELECT p FROM Pass p WHERE p.user = :user", Pass.class);
        getPass.setParameter("user", user);
        return getPass.getSingleResult();
    }

    public void getLocalDate(Pass pass) {
         TypedQuery<LocalDate> getPass = em.createQuery("SELECT p.dataDiScadenza FROM Pass p WHERE p.pass = :pass", LocalDate.class);
         getPass.setParameter("pass", pass);
        System.out.println(getPass.getSingleResult());

         /*     if (pass != null) {
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
        }*/
    }


    public Pass findPassById(UUID uuid) {
        TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p WHERE c.uuid = :id", Pass.class);
        query.setParameter("id", uuid);
        return query.getSingleResult();
    }

    public List<Pass> getAllPasses() {
        TypedQuery<Pass> query = em.createQuery("SELECT p FROM Pass p ", Pass.class);
        return query.getResultList();
    }

    public void setUserToPass(Pass pass,User user) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Query modify = em.createQuery("UPDATE Pass p SET p.user = :user WHERE p = :pass");
        modify.setParameter("pass", pass);
        modify.setParameter("user", user);
        modify.executeUpdate();
        System.out.println("Pass assegnato.");
        transaction.commit();
    }

}
