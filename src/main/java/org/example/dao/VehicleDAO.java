package org.example.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.example.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.UUID;




public class VehicleDAO {

        private final EntityManager em;

        public VehicleDAO(EntityManager em) {
            this.em = em;
        }



        //to save vehicle
        public void save (Vehicle vehicle){
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.persist(vehicle);
            transaction.commit();
            System.out.println("veicolo aggiunto");
        }

// FIND

//to find vehicle

        public Vehicle findVehicleByUUID (UUID uuid){
            return em.find(Vehicle.class, uuid);
        }

//to find tram

        public Tram findTramByUUID (UUID uuid){
            return em.find(Tram.class, uuid);
        }

//to find bus

        public Bus findBusByUUID (UUID uuid){
            return em.find(Bus.class, uuid);
        }

        //to find vehicle and delete
        public void findByUUIDAndDelete (UUID uuid){
            Vehicle found = this.findVehicleByUUID(uuid);
            if (found != null){
                EntityTransaction transaction = em.getTransaction();
                transaction.begin();
                em.remove(found);
                transaction.commit();
                System.out.println("vehicle deleted");
            }
            else {System.out.println("cannot find the vehicle");}
        }

    public List<MaintenanceRecord> getMaintenanceRecordsByPeriod(LocalDate maintenanceStartDate, LocalDate maintenanceEndDate) {
        TypedQuery<MaintenanceRecord> query = em.createNamedQuery("getMaintenanceRecordsByPeriod", MaintenanceRecord.class);
        query.setParameter("startDate", maintenanceStartDate);
        query.setParameter("endDate", maintenanceEndDate);
        return query.getResultList();
    }

    public List<Vehicle> getServiceVehicleByPeriod(LocalDate serviceStartDate, LocalDate serviceEndDate) {
        TypedQuery<Vehicle> query = em.createNamedQuery("getServiceVehicleByPeriod", Vehicle.class);
        query.setParameter("startDate", serviceStartDate);
        query.setParameter("endDate", serviceEndDate);
        return query.getResultList();
    }

    }


