package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Ticket extends ControlManagement{

    @ManyToOne
    private User user;
    private LocalDate dataDiVidimazione;

    private boolean annullato;

    @ManyToMany
    @JoinTable(name = "vehicles_ticket", joinColumns = @JoinColumn(name = "tickets"), inverseJoinColumns = @JoinColumn(name = "vehicle"))
    private List <Vehicle> vehicles;

    public Ticket(LocalDate dataEmissione, double prezzo, LocalDate dataDiVidimazione, Seller seller) {
        super(dataEmissione, prezzo);
        this.dataDiVidimazione = dataDiVidimazione;
        this.annullato = false;
    }



    public boolean isAnnullato() {
        return annullato;
    }

    public void setAnnullato(boolean annullato) {
        this.annullato = annullato;
    }

    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", dataDiVidimazione=" + dataDiVidimazione +
                ", annullato=" + annullato +
                ", vehicles=" + vehicles +
                '}';
    }
}
