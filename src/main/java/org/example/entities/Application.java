package org.example.entities;

import org.example.dao.RoutesDAO;
import org.example.dao.VehicleDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-TEAM1-BE");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();


        Route route1 = new Route("casd", "sadsa", 22.9, 33.9);
        RoutesDAO routesDAO = new RoutesDAO(em);
        routesDAO.save(route1);


        Bus bus1 = new Bus(50, LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 31), 45);
        VehicleDAO vehicleDao = new VehicleDAO(em);
        vehicleDao.save(bus1);



       // VehicleDAO maintenanceRecordDao = new VehicleDAO(em);
       // List<MaintenanceRecord> maintenanceRecords = maintenanceRecordDao.getMaintenanceRecordsByPeriod(LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31));


        System.out.println("Risultati della query per i periodi di manutenzione:");
        maintenanceRecord.forEach(maintenanceRecord -> {
            System.out.println("Inizio Manutenzione: " + maintenanceRecord.getMaintenanceStartDate() +
                    ", Fine Manutenzione: " + maintenanceRecord.getMaintenanceEndDate());
        });

       // List<Vehicle> vehicle = routesDAO.getVehicleByPeriod(LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31));





        em.close();
        emf.close();
    }
}
