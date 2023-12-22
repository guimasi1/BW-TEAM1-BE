package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ControlManagement {
    @Id
    @GeneratedValue
    private UUID uuid;
    private double prezzo;
    private LocalDate dataEmissione;

    @ManyToOne
    private Seller seller;

    // CONSTRUCTORS

    public  ControlManagement(){

    }

    public ControlManagement(LocalDate dataEmissione,double prezzo) {
        this.dataEmissione = dataEmissione;
        this.prezzo=prezzo;

    }

    public ControlManagement(double prezzo, LocalDate dataEmissione, Seller seller) {
        this.prezzo = prezzo;
        this.dataEmissione = dataEmissione;
        this.seller = seller;
    }

    // GETTER AND SETTER
    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    @Override
    public String toString() {
        return "ControlManagement{" +
                "uuid=" + uuid +
                ", prezzo=" + prezzo +
                ", dataEmissione=" + dataEmissione +
                '}';
    }


}

