package org.example;

import org.example.dao.RoutesDAO;
import org.example.entities.Route;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BW-TEAM1-BE");


    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        RoutesDAO routesDAO= new RoutesDAO(em);
        Route route = new Route("ommmm", "nope", 2010.0,1000.0);
        routesDAO.save(route);
        routesDAO.findRoutesByArrivalLocation("ov").forEach(System.out::println);
    }
}
