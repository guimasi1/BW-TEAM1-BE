package org.example.entities;

import org.example.dao.PassDAO;
import org.example.dao.RoutesDAO;
import org.example.dao.SellerDAO;
import org.example.dao.TicketDAO;

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
        Route route1= new Route("casd","sadsa", 22.9,33.9);
        RoutesDAO routesDAO = new RoutesDAO(em);
        routesDAO.save(route1);

        SellerDAO sd = new SellerDAO(em);
        Seller seller1 = new Seller("Roma", SellerType.RIVENDITORE, Service.IN_SERVICE);
        sd.saveSeller(seller1);

        TicketDAO td = new TicketDAO(em);
        Ticket ticket1 = new Ticket(LocalDate.of(2023, 12, 15), 5.50, LocalDate.of(2023, 12, 16), seller1);
        td.save(ticket1);
//        td.getAllTrips().forEach(System.out::println);
//        System.out.println(ticket1);
        td.getTicketSeller(SellerType.RIVENDITORE).forEach(System.out::println);


//        PassDAO pd = new PassDAO(em);
//        Pass pass1 = new Pass(LocalDate.of(2022, 12, 15), 110, Periodicity.MONTHLY, LocalDate.of(2023, 1, 15));
//       pd.save(pass1);
//        UUID cardUUID = UUID.fromString("17d86474-f103-483d-97d9-148da65f9014");
//        pd.checkValidityPassByCardNumber(cardUUID).forEach(System.out::println);

    }
}
