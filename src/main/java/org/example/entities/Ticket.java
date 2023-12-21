package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Ticket extends ControlManagement{
   /* @ManyToOne
    User user;*/
   @ManyToMany
   @JoinTable(name = "vehicles_ticket", joinColumns = @JoinColumn(name = "tickets"), inverseJoinColumns = @JoinColumn(name = "vehicle"))
   private List<Vehicle> vehicles;

    LocalDate dataDiVidimazione;

    public Ticket(LocalDate dataEmissione, double prezzo, LocalDate dataDiVidimazione) {
        super(dataEmissione, prezzo);
        this.dataDiVidimazione = dataDiVidimazione;
    }

    public Ticket(){}

    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "dataDiVidimazione=" + dataDiVidimazione +
                ", uuid=" + uuid +
                ", prezzo=" + prezzo +
                ", dataEmissione=" + dataEmissione +
                // ", user=" + user +
                '}';
    }
}
