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
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        routesDAO.save(route1);


        Bus bus1 = new Bus(50, LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31), LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 31), 45);
        vehicleDAO.save(bus1);

        Bus bus2 = new Bus(30, LocalDate.of(2020, 1, 1),LocalDate.of(2020, 3, 1), LocalDate.of(2020, 3, 2),LocalDate.of(2023, 12, 21), 40);
        vehicleDAO.save(bus2);


       vehicleDAO.getMaintenanceRecordsByPeriod(LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31)).forEach(System.out::println);;


        vehicleDAO.getServiceVehicleByPeriod(LocalDate.of(2024, 1, 1),LocalDate.of(2024, 12, 31)).forEach(System.out::println);;


        vehicleDAO.getMaintenanceRecordsByPeriod( LocalDate.of(2020, 1, 1),LocalDate.of(2020, 3, 1)).forEach(System.out::println);;


        vehicleDAO.getServiceVehicleByPeriod(LocalDate.of(2020, 3, 2),LocalDate.of(2023, 12, 21)).forEach(System.out::println);;



        Tram tram1 = new Tram(100, LocalDate.of(2021, 5, 1),LocalDate.of(2021, 7, 1), LocalDate.of(2021, 7, 2),LocalDate.of(2023, 5, 31), 8);
        vehicleDAO.save(tram1);

        Tram tram2 = new Tram(200, LocalDate.of(2022, 1, 1),LocalDate.of(2022, 3, 1), LocalDate.of(2022, 3, 2),LocalDate.of(2023, 12, 21), 11);
        vehicleDAO.save(tram2);


        vehicleDAO.getMaintenanceRecordsByPeriod(LocalDate.of(2021, 5, 1),LocalDate.of(2021, 7, 1)).forEach(System.out::println);;


        vehicleDAO.getServiceVehicleByPeriod(LocalDate.of(2021, 7, 2),LocalDate.of(2023, 5, 31)).forEach(System.out::println);;


        vehicleDAO.getMaintenanceRecordsByPeriod( LocalDate.of(2022, 1, 1),LocalDate.of(2022, 3, 1)).forEach(System.out::println);;


        vehicleDAO.getServiceVehicleByPeriod(LocalDate.of(2022, 3, 2),LocalDate.of(2023, 12, 21)).forEach(System.out::println);;







     /*   maintenanceRecord.forEach(maintenanceRecord -> {
            System.out.println("Inizio Manutenzione: " + maintenanceRecord.getMaintenanceStartDate() +
                    ", Fine Manutenzione: " + maintenanceRecord.getMaintenanceEndDate());
        }); */

       // List<Vehicle> vehicle = routesDAO.getVehicleByPeriod(LocalDate.of(2023, 1, 1),LocalDate.of(2023, 12, 31));


        em.close();
        emf.close();
    }
}
