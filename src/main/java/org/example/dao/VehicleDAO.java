package org.example.dao;

import java.util.UUID;

import org.example.entities.Bus;
import org.example.entities.Tram;
import org.example.entities.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
    }


