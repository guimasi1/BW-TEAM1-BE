package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
public class Pass extends ControlManagement{
    @OneToOne
    @JoinColumn(name = "user_id")
     private User user;

    @Enumerated(EnumType.STRING)
     private Periodicity periodicity;
     private LocalDate dataDiScadenza;

     public Pass() {}
    public Pass(LocalDate dataEmissione, double prezzo, Periodicity periodicity, LocalDate dataDiScadenza) {
        super(dataEmissione, prezzo);
        this.periodicity = periodicity;
        if(periodicity == Periodicity.MONTHLY) {
            this.dataDiScadenza = this.getDataEmissione().plusDays(30);
        } else {
            this.dataDiScadenza = this.getDataEmissione().plusDays(7);

        }
     }

    public Pass(LocalDate dataEmissione, double prezzo, Periodicity periodicity) {
        super(dataEmissione, prezzo);
        this.periodicity = periodicity;
        if(periodicity == Periodicity.MONTHLY) {
            this.dataDiScadenza = this.getDataEmissione().plusDays(30);
        } else {
            this.dataDiScadenza = this.getDataEmissione().plusDays(7);

        }
    }

    public Pass(LocalDate dataEmissione, double prezzo, User user, Periodicity periodicity) {
        super(dataEmissione, prezzo);
        this.user = user;
        this.periodicity = periodicity;
        if(periodicity == Periodicity.MONTHLY) {
            this.dataDiScadenza = this.getDataEmissione().plusDays(30);
        } else {
            this.dataDiScadenza = this.getDataEmissione().plusDays(7);

        }
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public LocalDate getDataDiScadenza() {
        return dataDiScadenza;
    }

    public void setDataDiScadenza(LocalDate dataDiScadenza) {
        this.dataDiScadenza = dataDiScadenza;
    }

    @Override
    public String toString() {
        return "Pass{" +
               // "user=" + user +
                ", periodicity=" + periodicity +
                ", dataDiScadenza=" + dataDiScadenza +
                '}';
    }
}
