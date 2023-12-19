package org.example;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ControlManagement {
        @Id
        @GeneratedValue
        UUID uuid;
        double prezzo;
        LocalDate dataEmissione;

        //User utente;

        public  ControlManagement(){

        }

        public ControlManagement(LocalDate dataEmissione,double prezzo) {
            this.dataEmissione = dataEmissione;
            this.prezzo=prezzo;

        }



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
                    //", utente=" + utente +
                    '}';
        }
    }

