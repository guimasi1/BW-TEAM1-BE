package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
public class Ticket extends ControlManagement{

    @ManyToOne
    private User user;
    private LocalDate dataDiVidimazione;

    @ManyToMany
    @JoinTable(name = "vehicles_ticket", joinColumns = @JoinColumn(name = "tickets"), inverseJoinColumns = @JoinColumn(name = "vehicle"))
    private List <Vehicle> vehicles;

    public  Ticket(){

    }

    public Ticket(LocalDate dataEmissione, double prezzo,LocalDate dataDiVidimazione) {
        super(dataEmissione, prezzo);
        this.dataDiVidimazione=dataDiVidimazione;
    }



    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
    }

    public void setDataDiVidimazione(LocalDate dataDiVidimazione) {
        this.dataDiVidimazione = dataDiVidimazione;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }



    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", dataDiVidimazione=" + dataDiVidimazione +
                ", vehicles=" + vehicles +
                '}';
    }
}
