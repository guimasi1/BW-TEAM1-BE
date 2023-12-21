package org.example.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
    @Table(name = "trams")
    @DiscriminatorValue("Tram")
    public class Tram extends Vehicle{
        @Column(name = "numero_vagoni")
        private int numeroVagoni;

    public Tram(int capacity, LocalDate maintenanceStartDate, LocalDate maintenanceEndDate, LocalDate serviceStartDate, LocalDate serviceEndDate, int numeroVagoni) {
        super(capacity, maintenanceStartDate, maintenanceEndDate, serviceStartDate, serviceEndDate);
        this.numeroVagoni = numeroVagoni;
    }

    public Tram(){

        }

        public int getNumeroVagoni() {
            return numeroVagoni;
        }

        public void setNumeroVagoni(int numeroVagoni) {
            this.numeroVagoni = numeroVagoni;
        }

        @Override
        public String toString() {
            return "Tram{" +
                    "numeroVagoni=" + numeroVagoni +
                    '}';
        }
    }
