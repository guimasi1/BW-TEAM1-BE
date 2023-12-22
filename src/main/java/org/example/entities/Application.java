package org.example.entities;

import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;
import org.example.dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Application {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("BW-TEAM1-BE");

    public static void main(String[] args) {
        Faker faker = new Faker();
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
//        Route route1= new Route("asdasd","aaaaa", 222.0,333.0);
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
        SellerDAO sellerDAO = new SellerDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);

        // travel();
        // test();
        // createFakeUsers();
        // createVehicles();
        // createFakeRoutesAndTrips();
        // showAllData(scanner);
        methodSTART(scanner);
       /*Ticket ticket =  controlManagementDAO.findTicketById(UUID.fromString("6be11cf7-e304-4ed5-aa10-f0c8d1cf2e51"));
       Vehicle vehicle = vehicleDAO.findVehicleByUUID(UUID.fromString("02f1fd41-b515-4427-bd87-c4cc8ce2499b"));
        controlManagementDAO.validateTicket(ticket,LocalDate.now(), vehicle);*/
//        controlManagementDAO.getTicketsByVehicleAndPeriod(vehicle, LocalDate.parse("2020-01-01"), LocalDate.now()).forEach(System.out::println);
        em.close();
        emf.close();
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

    public static void showAllData(Scanner scanner) {
        int choice = -1;
        EntityManager em = emf.createEntityManager();
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
        SellerDAO sellerDAO = new SellerDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);
        do {
            System.out.println("Prema 1 per visualizzare tutti gli utenti, " +
                    "2 per i mezzi di trasporto, " +
                    "3 per le tratte, " +
                    "4 per i rivenditori, " +
                    "5 per gli abbonamenti rilasciati. " +
                    "Se vuole uscire dal menu prema 0.");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    userDAO.getAllUsers().forEach(System.out::println);
                    break;
                case 2:
                    vehicleDAO.getAllVehicles().forEach(System.out::println);
                    break;
                case 3:
                    routesDAO.getAllRoutes().forEach(System.out::println);
                    break;
                case 4:
                    sellerDAO.getAllSellers().forEach(System.out::println);
                    break;
                case 5:
                    controlManagementDAO.getAllPass().forEach(System.out::println);
                    break;
            }

        } while(choice <-1 || choice >6 );

    }

    public static void methodSTART(Scanner scanner) {
        EntityManager em = emf.createEntityManager();
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);
        RoutesDAO routesDAO = new RoutesDAO(em);


        String name = null;
        String surname = null;
        int age = 11;
        do {
            System.out.println("Inserisca il suo nome.");
            name = scanner.nextLine();
        } while(name == null);
        if(!name.equals("admin")) {
            do {
                System.out.println("Inserisca il suo cognome.");
                surname = scanner.nextLine();
            } while (surname == null);

            do {
                System.out.println("Inserisca la sua età.");
                age = Integer.parseInt(scanner.nextLine());
            } while (age < 12 || age > 120);

            User myUser = new User(name, surname, age);

            ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);


            userDAO.saveUser(myUser);
            User userDatabase = userDAO.getUserByNameAndSurname(name, surname);
            int choicePassOrTicket;
            do {
                System.out.println("Benvenuto " + name + " " + surname + "! Vuole acquistare dei biglietti o un abbonamento? " +
                        "Inserisca 1 per i biglietti, 2 per l'abbonamento.");
                choicePassOrTicket = Integer.parseInt(scanner.nextLine());
                if (choicePassOrTicket == 1) {
                    int numberOfTickets;
                    do {
                        System.out.println("Quanti biglietti vuole acquistare? Inserisca un numero da 1 a 20");
                        numberOfTickets = Integer.parseInt(scanner.nextLine());
                    } while (numberOfTickets < 1 || numberOfTickets > 20);
                    for (int i = 0; i < numberOfTickets; i++) {
                        Ticket ticket = new Ticket(LocalDate.now(), 1.5, userDatabase);
                        controlManagementDAO.save(ticket);
                    }

                }
                if (choicePassOrTicket == 2) {
                    int choicePass;
                    Periodicity periodicity = null;
                    double cost = 0;
                    do {
                        System.out.println("Prema 1 per un abbonamento settimanale, 2 per quello mensile.");
                        choicePass = Integer.parseInt(scanner.nextLine());
                        if (choicePass == 1) {
                            periodicity = Periodicity.WEEKLY;
                            cost = 15.0;
                        }
                        if (choicePass == 2) {
                            periodicity = Periodicity.MONTHLY;
                            cost = 30.0;
                        }
                    } while (choicePass < 1 || choicePass > 2);
                    Pass pass = new Pass(LocalDate.now(), cost, userDatabase, periodicity);
                    controlManagementDAO.save(pass);
                }
            } while (choicePassOrTicket < 1 || choicePassOrTicket > 2);

            if (choicePassOrTicket == 1) {
                System.out.println("Ecco a lei. Questi sono i suoi biglietti:");
                controlManagementDAO.getTicketsByUser(myUser).forEach(System.out::println);
            }

            System.out.println("Ecco la lista dei viaggi attualmente disponibili:");
            tripsDAO.getAllTrips().forEach(System.out::println);
            int choiceTrip;
            do {
                System.out.println("Quale percorso vuole fare? Inserisca un numero da 0 a " + tripsDAO.getAllTrips().size());
                choiceTrip = Integer.parseInt(scanner.nextLine());
            } while (choiceTrip < 0 || choiceTrip > tripsDAO.getAllTrips().size());

            Trip currentTrip = tripsDAO.getAllTrips().get(choiceTrip);
            Vehicle currentVehicleTrip = tripsDAO.getVehicleByTrip(currentTrip);
            int choiceWhichTicket;
            do {
                System.out.println("Questi sono i biglietti che può utilizzare:");
                controlManagementDAO.getTicketsByUser(myUser).forEach(System.out::println);
                System.out.println("Quale biglietto vuole vidimare? Prema da 0 a " + (controlManagementDAO.getTicketsByUser(myUser).size() - 1));
                choiceWhichTicket = Integer.parseInt(scanner.nextLine());

            } while (choiceWhichTicket < 0 || choiceWhichTicket > controlManagementDAO.getTicketsByUser(myUser).size() - 1);
            controlManagementDAO.validateTicket(controlManagementDAO.getTicketsByUser(myUser).get(choiceWhichTicket), LocalDate.now(), currentVehicleTrip);
        }
        if(name.equals("admin")) {
            int choiceViewVehicles;
            do {
                System.out.println("Prema 1 - per visualizzare i mezzi di trasporto attualmente in manutenzione, " +
                        "2 - per quelli in servizio, " +
                        "3 - per visualizzare i biglietti e gli abbonamenti emessi in un dato periodo di tempo, " +
                        "4 - per verificare la validità di un abbonamento, " +
                        "5 - per visualizzare il numero di volte che un mezzo percorre una tratta.");
                choiceViewVehicles = Integer.parseInt(scanner.nextLine());
            } while (choiceViewVehicles < 0 || choiceViewVehicles > 5);
            switch (choiceViewVehicles) {
                case 1:
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
            }
        }
        scanner.close();
    }
    public static void test() {
        EntityManager em = emf.createEntityManager();

        /*routesDAO.save(route1);*/
        Bus bus1 = new Bus(20, LocalDate.now(),300);
        Tram tram1 = new Tram(30, LocalDate.now(),10);
       /* vehicleDAO.save(bus1);
        vehicleDAO.save(tram1);*/
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
        SellerDAO sellerDAO = new SellerDAO(em);
        UserDAO userDAO = new UserDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);
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
    }
    public static void createFakeRoutesAndTrips() {
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);
        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            Route route = new Route(faker.address().streetAddress(),faker.address().streetAddress(), random.nextDouble(10,120));
            routesDAO.save(route);
        }

        for (int i = 0; i < 40; i++) {
            Vehicle vehicle = vehicleDAO.getAllVehicles().get(random.nextInt(0,40));
            Route route = routesDAO.getAllRoutes().get(random.nextInt(0,40));
            Trip trip = new Trip(LocalDateTime.of(2023,12,22, random.nextInt(17,24),
                    random.nextInt(0,60),0),vehicle,route);
            tripsDAO.save(trip);
        }
    }
}
