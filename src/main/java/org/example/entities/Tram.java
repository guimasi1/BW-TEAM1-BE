package org.example.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "trams")
@DiscriminatorValue("Tram")
public class Tram extends Vehicle{
    @Column(name = "numero_vagoni")
    private int numeroVagoni;

    // CONSTRUCTORS
    public Tram(){}


    public Tram(int capacity, List<MaintenanceRecord> maintenanceRecords, LocalDate serviceStartDate, int numeroVagoni) {
        super(capacity, maintenanceRecords, serviceStartDate);
        this.numeroVagoni = numeroVagoni;
    }

    public Tram(int capacity, LocalDate serviceStartDate, int numeroVagoni) {
        super(capacity, serviceStartDate);
        this.numeroVagoni = numeroVagoni;
    }

    // GETTER AND SETTER

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
                    " uuid=" + this.getUuid() +
                    '}';
        }
}
