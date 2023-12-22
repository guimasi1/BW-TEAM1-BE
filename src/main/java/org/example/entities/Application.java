package org.example.entities;

import org.example.dao.RoutesDAO;
import org.example.dao.VehicleDAO;
import com.github.javafaker.Faker;
import net.bytebuddy.asm.Advice;
import org.example.dao.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
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
      /*  createFakeUsers();
        createVehicles();
        createFakeRoutesAndTrips();
        createFakeSellers();
        Lorenza();
        createFakeTicketsWithSeller();
        createUsersWithPasses();*/
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
        for (int i = 0; i < 3; i++) {
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


        em.close();
        emf.close();
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
            userDAO.saveUser(myUser);
            User userDatabase = userDAO.getUserByNameAndSurname(name, surname);
            ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
            SellerDAO sellerDAO = new SellerDAO(em);
            sellerDAO.getAllSellers().forEach(System.out::println);
            System.out.println("Da quale rivenditore vuole acquistare i suoi biglietti o il suo abbonamento? " +
                    "Inserisca un numero da 0 a 2");
            int choiceSeller = Integer.parseInt(scanner.nextLine());
            Seller sellerDatabase = sellerDAO.getAllSellers().get(choiceSeller);


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
                       // Ticket ticket = new Ticket(LocalDate.now(), 1.5, sellerDatabase,userDatabase);
                        Ticket ticket = new Ticket(1.5, LocalDate.now(), sellerDatabase,userDatabase);
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
                        "5 - per visualizzare il numero di volte che un mezzo percorre una tratta, " +
                        "6 - per visualizzare il numero di biglietti emessi da un rivenditore.");
                choiceViewVehicles = Integer.parseInt(scanner.nextLine());
            } while (choiceViewVehicles < 0 || choiceViewVehicles > 6);
            switch (choiceViewVehicles) {
                case 1:
                    System.out.println("Inserire la data di inizio manutenzione");
                    System.out.println("Inserire l'anno");
                    int yearMaintenance = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int monthMaintenance = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int dayMaintenance = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceMaintenancePeriod = LocalDate.of(yearMaintenance,monthMaintenance,dayMaintenance);
                    System.out.println("Inserire la data di fine manutenzione");
                    System.out.println("Inserire l'anno");
                    int year2Maintenance = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int month2Maintenance = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int day2Maintenance = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceMaintenanceEndPeriod = LocalDate.of(year2Maintenance,month2Maintenance,day2Maintenance);
                    vehicleDAO.getMaintenanceRecordsByPeriod(choiceMaintenancePeriod, choiceMaintenanceEndPeriod).forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Inserire la data di inizio servizio");
                    System.out.println("Inserire l'anno");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int month = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int day = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceStartPeriod = LocalDate.of(year,month,day);
                    System.out.println("Inserire la data di fine servizio");
                    System.out.println("Inserire l'anno");
                    int year2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int month2 = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int day2 = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceEndPeriod = LocalDate.of(year2,month2,day2);

                    vehicleDAO.getServiceVehicleByPeriod(choiceEndPeriod, choiceStartPeriod).forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Inserire la data di inizio emissione");
                    System.out.println("Inserire l'anno");
                    int yearTicket = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int monthTicket = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int dayTicket = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceTicketPeriod = LocalDate.of(yearTicket,monthTicket,dayTicket);
                    System.out.println("Inserire la data di fine emissione");
                    System.out.println("Inserire l'anno");
                    int year2Ticket = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il mese");
                    int month2Ticket = Integer.parseInt(scanner.nextLine());
                    System.out.println("Inserire il giorno");
                    int day2Ticket = Integer.parseInt(scanner.nextLine());
                    LocalDate choiceTicketEndPeriod = LocalDate.of(year2Ticket,month2Ticket,day2Ticket);
                    ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
                    System.out.println(controlManagementDAO.numberTicket(choiceTicketPeriod,choiceTicketEndPeriod));
                    break;
                case 4:
                    userDAO.getAllUsers().forEach(System.out::println);
                    System.out.println("Scegliere l'user di cui si vuole sapere se l'abbonamento è valido o meno. " +
                            "Inserire un numero fra 0 e " + (userDAO.getAllUsers().size() -1));
                    int choiceUser = Integer.parseInt(scanner.nextLine());
                    User userDatabase = userDAO.getAllUsers().get(choiceUser);
                    System.out.println(userDatabase);
                    ControlManagementDAO controlManagementDAO2 = new ControlManagementDAO(em);
                    // Pass passDatabase = controlManagementDAO2.getAllPasses().get(0);
                   Pass passDatabase = controlManagementDAO2.findPassByIDTEST(UUID.fromString("6be91ab6-03eb-4c1f-8685-0cab1afc246f"));
                    // Pass passDatabase = controlManagementDAO2.getPassByUser(userDatabase);
                    System.out.println(passDatabase);
                    controlManagementDAO2.getLocalDate(passDatabase);
                    //controlManagementDAO2.isValid(passDatabase);
                    break;
                case 5:
                    vehicleDAO.getAllVehicles().forEach(System.out::println);
                    System.out.println("Scegliere il veicolo di cui si vuole sapere il numero di volte che ha pecorso una tratta.");
                    int choiceVehicle = Integer.parseInt(scanner.nextLine());
                    routesDAO.getAllRoutes().forEach(System.out::println);
                    System.out.println("Scegliere la tratta");
                    int choiceRoute = Integer.parseInt(scanner.nextLine());
                    System.out.println(tripsDAO.getNumberOfTripsByVehicle(vehicleDAO.getAllVehicles().get(choiceVehicle),
                            routesDAO.getAllRoutes().get(choiceRoute)));
                    break;
                case 6:
                    ControlManagementDAO controlManagementDAO1 = new ControlManagementDAO(em);
                    SellerDAO sellerDAO = new SellerDAO(em);
                    sellerDAO.getAllSellers().forEach(System.out::println);
                    System.out.println("Di quale rivenditore vuole visualizzare il numero di biglietti o abbonamenti venduti? Inserisca un numero da 0 a 2");
                    int choiceSellerTickets = Integer.parseInt(scanner.nextLine());
                    Seller sellerDatabase = sellerDAO.getAllSellers().get(choiceSellerTickets);
                    controlManagementDAO1.getTicketsBySeller(sellerDatabase).forEach(System.out::println);
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

    public static void createFakeTicketsWithSeller () {
        EntityManager em = emf.createEntityManager();
        SellerDAO sellerDAO = new SellerDAO(em);
        TicketDAO ticketDAO = new TicketDAO(em);
        UserDAO userDAO = new UserDAO(em);
        User user = new User("Franco", "Nanni", 80);
        userDAO.saveUser(user);
        User userDatabase = userDAO.getUserByNameAndSurname("Franco", "Nanni");
        Seller seller = new Seller("Tabacchino - 2",SellerType.AUTHORIZED,Service.IN_SERVICE);
        sellerDAO.saveSeller(seller);
        Seller sellerDatabase = sellerDAO.getSellerByPlace("Tabacchino - 2");
        for (int i = 0; i < 10; i++) {

            Ticket ticket = new Ticket(1.5,LocalDate.parse("2023-12-20"),sellerDatabase,userDatabase);
            ticketDAO.save(ticket);
        }


    }

    public static void createUsersWithPasses() {
        EntityManager em = emf.createEntityManager();
        UserDAO userDAO = new UserDAO(em);
        PassDAO passDAO = new PassDAO(em);
        ControlManagementDAO controlManagementDAO = new ControlManagementDAO(em);
        Pass pass = new Pass(LocalDate.now(),30.0,Periodicity.MONTHLY);
        controlManagementDAO.save(pass);
        Pass passDatabase = controlManagementDAO.getAllPasses().get(0);
        User user = new User("Marco", "Colasanti",54, passDatabase);
        userDAO.saveUser(user);
        User userDatabase = userDAO.getUserByNameAndSurname("Marco", "Colasanti");
        controlManagementDAO.setUserToPass(passDatabase, userDatabase);

    }

    public static void createFakeSellers ( ) {
        EntityManager em = emf.createEntityManager();
        SellerDAO sellerDAO = new SellerDAO(em);
        Seller seller = new Seller("Tabacchino", SellerType.AUTHORIZED, Service.IN_SERVICE);
        Seller seller2 = new Seller("Distributore automatico", SellerType.AUTOMATIC, Service.IN_SERVICE);
        Seller seller3 = new Seller("Controllore", SellerType.AUTHORIZED, Service.IN_SERVICE);
        sellerDAO.saveSeller(seller);
        sellerDAO.saveSeller(seller2);
        sellerDAO.saveSeller(seller3);
    }
    public static void createFakeRoutesAndTrips() {
        Faker faker = new Faker();
        EntityManager em = emf.createEntityManager();
        RoutesDAO routesDAO = new RoutesDAO(em);
        VehicleDAO vehicleDAO = new VehicleDAO(em);
        TripsDAO tripsDAO = new TripsDAO(em);
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            Route route = new Route(faker.address().streetAddress(), faker.address().streetAddress(), random.nextDouble(10, 120));
            routesDAO.save(route);
        }
        for (int i = 0; i < 3; i++) {
            Vehicle vehicle = vehicleDAO.getAllVehicles().get(random.nextInt(0,3));
            Route route = routesDAO.getAllRoutes().get(random.nextInt(0,3));
            Trip trip = new Trip(LocalDateTime.of(2023,12,22, random.nextInt(17,24),
                    random.nextInt(0,60),0),vehicle,route);
            tripsDAO.save(trip);
        }
    }
    public static void Lorenza(){

        EntityManager em = emf.createEntityManager();
        VehicleDAO vehicleDAO = new VehicleDAO(em);

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
        System.out.println("eccolo");
        vehicleDAO.getServiceVehicleByPeriod(LocalDate.of(2022, 3, 2),LocalDate.of(2023, 12, 21)).forEach(System.out::println);;
    }


}
