package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NamedQuery(name = "getNumberTicketsBySellerType", query = "SELECT a FROM Ticket a WHERE a.seller.sellerType = :sellerType")
@Entity
public class Ticket extends ControlManagement{

    @ManyToOne
    private User user;
    private LocalDate dataDiVidimazione;

    @ManyToMany
    @JoinTable(name = "vehicles_ticket", joinColumns = @JoinColumn(name = "tickets"), inverseJoinColumns = @JoinColumn(name = "vehicle"))
    private List <Vehicle> vehicles;

    public Ticket(LocalDate dataEmissione, double prezzo, LocalDate dataDiVidimazione, Seller seller) {
        super(dataEmissione, prezzo, seller);
        this.dataDiVidimazione = dataDiVidimazione;
    }

    public Ticket(){}

    public LocalDate getDataDiVidimazione() {
        return dataDiVidimazione;
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
