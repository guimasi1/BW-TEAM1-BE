package org.example.dao;

import org.example.entities.Route;
import org.example.entities.Trip;
import org.example.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class TripsDAO {
    private final EntityManager em;

    public TripsDAO(EntityManager em) {
        this.em = em;
    }
    public void save(Trip trip) {
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.persist(trip);

        transaction.commit();

        System.out.println("Trip saved.");
    }

    public Trip findById(UUID id) {
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t WHERE t.uuid = :id", Trip.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public void removeById(UUID id) {
        em.remove(this.findById(id));
    }

    public List<Trip> getAllTrips() {
        TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
        return query.getResultList();
    }
    public Long getTravelCountByVehicleAndRoute(Vehicle vehicle, Route route) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM Trip t WHERE t.vehicle = :vehicle AND" +
                " t.route = :route", Long.class);
        query.setParameter("vehicle", vehicle);
        query.setParameter("route", route);

        return query.getSingleResult();
    }
    public List<Duration> getDurationByVehicleAndRoute(Vehicle vehicle, Route route) {
        TypedQuery<Duration> query = em.createQuery("SELECT t.duration FROM Trip t WHERE t.vehicle = :vehicle AND" +
                " t.route = :route", Duration.class);
        query.setParameter("vehicle", vehicle);
        query.setParameter("route", route);

        return query.getResultList();
    }

    public Vehicle getVehicleByTrip(Trip trip) {
        TypedQuery<Vehicle> query = em.createQuery("SELECT t.vehicle FROM Trip t WHERE t = :trip", Vehicle.class);
        query.setParameter("trip", trip);
        return query.getSingleResult();
    }
    public Long getNumberOfTripsByVehicle(Vehicle vehicle, Route route) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(t) FROM Trip t WHERE t.vehicle = :vehicle AND t.route = :route", Long.class);
        query.setParameter("vehicle", vehicle);
        query.setParameter("route", route);
        return query.getSingleResult();
    }
}
