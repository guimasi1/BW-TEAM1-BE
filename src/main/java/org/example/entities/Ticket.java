package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Ticket extends ControlManagement{
    @ManyToOne
    User user;
    LocalDate dataDiVidimazione;

    public Ticket(LocalDate dataEmissione, double prezzo, LocalDate dataDiVidimazione) {
        super(dataEmissione, prezzo);
        this.dataDiVidimazione = dataDiVidimazione;
    }

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
                ", user=" + user +
                '}';
    }
}
