package org.example.entities;

import org.example.dao.*;

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
//        Route route1= new Route("casd","sadsa", 22.9,33.9);
//        RoutesDAO routesDAO = new RoutesDAO(em);
//        routesDAO.save(route1);

        UserDAO ud = new UserDAO(em);
        User user1 = new User("Sarah", "Guarneri", 26);
//        ud.saveUser(user1);
        User userFound = ud.findUserByID(UUID.fromString("e2685d21-f779-4014-96e0-bba9974ac511"));

        SellerDAO sd = new SellerDAO(em);
        Seller seller1 = new Seller("Roma", SellerType.RIVENDITORE, Service.IN_SERVICE);
        sd.saveSeller(seller1);
        Seller seller2 = sd.findSellerByID(UUID.fromString("14d53e1f-6b38-4c70-ae9c-2c5b99060970"));

        PassDAO pd = new PassDAO(em);
        Pass pass1 = new Pass(LocalDate.of(2022, 12, 15), 105, Periodicity.MONTHLY, LocalDate.of(2023, 01, 15), seller2, userFound);
        pd.save(pass1);
//        Pass passToAddUser = pd.findById(UUID.fromString("0cc66162-0ae5-4909-ab46-469f2f3207b6"));
////        pd.addPassToUser(user1, passToAddUser);


//        TicketDAO td = new TicketDAO(em);
//        Ticket ticket1 = new Ticket(LocalDate.of(2023, 12, 15), 5.50, LocalDate.of(2023, 12, 16), seller1);
//        td.save(ticket1);
//        td.getAllTrips().forEach(System.out::println);
////        System.out.println(ticket1);
//        long ticketBySeller = td.getTicketSeller(SellerType.RIVENDITORE);
//        System.out.println(ticketBySeller);








//        boolean isValid = pd.checkValidityPassByCardNumber(UUID.fromString("9b8cb22d-c904-41ef-920f-ff337a89b7e9"));
//        System.out.println(isValid);

//        System.out.println(pd.getPass(UUID.fromString("95e36f9e-6c7c-4dda-9df6-bc98548b4b93")));




//        ControlManagementDAO md = new ControlManagementDAO(em);
//        md.getTicketSeller(SellerType.RIVENDITORE).forEach(System.out::println);


//        PassDAO pd = new PassDAO(em);
//        Pass pass1 = new Pass(LocalDate.of(2022, 12, 15), 110, Periodicity.MONTHLY, LocalDate.of(2023, 1, 15));
//       pd.save(pass1);
//        UUID cardUUID = UUID.fromString("17d86474-f103-483d-97d9-148da65f9014");
//        pd.checkValidityPassByCardNumber(cardUUID).forEach(System.out::println);

    }
}
