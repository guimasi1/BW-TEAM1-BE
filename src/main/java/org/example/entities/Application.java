package org.example.entities;

import org.example.dao.RoutesDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-TEAM1-BE");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        Route route1= new Route("casd","sadsa", 22.9,33.9);
        RoutesDAO routesDAO = new RoutesDAO(em);
        routesDAO.save(route1);

        // Creazione di un nuovo Ticket
        Ticket ticket1 = createTicket();
        Ticket ticket2 = createTicket();

        // Veicolo di test
        Vehicle testVehicle = createTestVehicle();

        // Vidima  sul veicolo di test
        testVehicle.vidimaTicket(ticket1);

        // num bigl timbrati veicolo test
        int numeroBigliettiVidimati = testVehicle.getNumeroBigliettiVidimati();
        System.out.println("Numero di biglietti vidimati sul veicolo di test: " + numeroBigliettiVidimati);


    }

    private static Ticket createTicket() {

        Seller seller = new Seller();


        return new Ticket(LocalDate.now(), 20.0, LocalDate.now(), seller);
    }

    private static Vehicle createTestVehicle() {


        return new Bus("Bus", 50, LocalDate.now(), LocalDate.now().plusDays(30), LocalDate.now(), LocalDate.now().plusDays(30));
    }




}
