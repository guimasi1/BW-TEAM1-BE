package org.example.entities;

import com.github.javafaker.Faker;
import org.example.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("BW-TEAM1-BE");

    public static void main(String[] args) {
        Faker faker = new Faker();
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        Route route1= new Route("asdasd","aaaaa", 222.0,333.0);
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
        SellerDAO sellerDAO = new SellerDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);

        /*routesDAO.save(route1);*/
        Bus bus1 = new Bus(20, LocalDate.now(),300);
        Tram tram1 = new Tram(30, LocalDate.now(),10);
       /* vehicleDAO.save(bus1);
        vehicleDAO.save(tram1);*/

        LocalDateTime start = LocalDateTime.of(2023,10,1,10,30,0);
        LocalDateTime end = LocalDateTime.of(2023,10,1,11,30,0);

        vehicleDAO.getAllVehicles().forEach(System.out::println);
        routesDAO.getAllRoutes().forEach(System.out::println);
        Route savedRoute = routesDAO.findById(UUID.fromString("23686d04-d6d1-4d6a-aee7-4ced47edcbb3"));
        Vehicle savedVehicle = vehicleDAO.findVehicleByUUID(UUID.fromString("df9952cd-4ec3-4688-8347-6ee8061ee75c"));
        Trip trip2 = new Trip(start,end,savedVehicle,savedRoute);
        //tripsDAO.save(trip2);
        // tripsDAO.getAllTrips().forEach(System.out::println);
        // System.out.println(tripsDAO.getTravelCountByVehicleAndRoute(savedVehicle,savedRoute));
        System.out.println(tripsDAO.getDurationByVehicleAndRoute(savedVehicle,savedRoute));

        // createFakeUsers();

         createVehicles();
    }

    public static void createFakeUsers() {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();
        UserDAO userDAO = new UserDAO(em);

        for (int i = 0; i < 20; i++) {
            User user = new User(faker.name().firstName(), faker.name().lastName(),faker.number().numberBetween(8,100));
            userDAO.saveUser(user);
        }
    }

    public static void createVehicles() {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker();
        VehicleDAO vehicleDAO= new VehicleDAO(em);
        for (int i = 0; i < 20; i++) {
            Bus bus = new Bus(faker.number().numberBetween(20,40),LocalDate.parse("2022-10-10"),300);
            Tram tram = new Tram(faker.number().numberBetween(20,40),LocalDate.parse("2022-10-10"),10);
            vehicleDAO.save(bus);
            vehicleDAO.save(tram);
        }
    }
}
