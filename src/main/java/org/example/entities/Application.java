package org.example.entities;

import org.example.dao.RoutesDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-TEAM1-BE");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EntityManager em = emf.createEntityManager();
        Route route1= new Route("casd","sadsa", 22.9,33.9);
        RoutesDAO routesDAO = new RoutesDAO(em);
        routesDAO.save(route1);

    }
}
