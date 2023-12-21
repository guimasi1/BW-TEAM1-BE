package org.example.entities;

import org.example.dao.ControlManagementDAO;
import org.example.dao.RoutesDAO;
import org.example.dao.TicketDAO;

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
        //routesDAO.save(route1);

        Ticket ti=new Ticket(LocalDate.of(2022,12,23),1.50,LocalDate.of(2000,11,22));
        Ticket ticket=new Ticket(LocalDate.of(2022,12,23),1.50,LocalDate.of(2000,11,22));

        ControlManagementDAO controlManagementDAO=new ControlManagementDAO(em);

        TicketDAO ticketDAO=new TicketDAO(em);

        controlManagementDAO.save(ticket);

        controlManagementDAO.save(ti);

        controlManagementDAO.ticketTracking(LocalDate.of(2022,12,23),LocalDate.of(2024,1,12)).forEach(System.out::println);
        controlManagementDAO.numberTicket(LocalDate.of(2022,12,23),LocalDate.of(2024,1,12));



        //controlManagementDAO.save(ticket);


        System.out.println( controlManagementDAO.numberTicket(LocalDate.of(2022,12,23),LocalDate.of(2024,1,12)));


        //ticketDAO.save(ticket);

    }
}
