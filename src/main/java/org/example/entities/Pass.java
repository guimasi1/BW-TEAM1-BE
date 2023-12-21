package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@NamedQuery(name = "getNumberPassBySellerType", query = "SELECT a FROM Pass a WHERE a.seller.sellerType = :sellerType")
@NamedQuery(name = "checkValidityPassByCardNumber", query = "SELECT a FROM Pass a WHERE a.user.card = :cardNumber")
@Entity
public class Pass extends ControlManagement{
    @OneToOne
     private User user;
     private Periodicity periodicity;
     private LocalDate dataDiScadenza;

    public Pass(LocalDate dataEmissione, double prezzo, Periodicity periodicity, LocalDate dataDiScadenza, Seller seller) {
        super(dataEmissione, prezzo ,seller);
        this.periodicity = periodicity;
        this.dataDiScadenza = dataDiScadenza;
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
                "user=" + user +
                ", periodicity=" + periodicity +
                ", dataDiScadenza=" + dataDiScadenza +
                '}';
    }
}
