package org.example.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "bus")
@DiscriminatorValue("Bus")
public class Bus extends Vehicle{
    @Column(name = "cilindrata_motore")
    private int cilindrataMotore;

    // CONSTRUCTORS
    public Bus(){}


    public Bus(int capacity, LocalDate maintenanceStartDate, LocalDate maintenanceEndDate, LocalDate serviceStartDate, LocalDate serviceEndDate, int cilindrataMotore) {
        super(capacity, maintenanceStartDate, maintenanceEndDate, serviceStartDate, serviceEndDate);
        this.cilindrataMotore = cilindrataMotore;
    }

    public Bus(int capacity, List<MaintenanceRecord> maintenanceRecords, LocalDate serviceStartDate, int cilindrataMotore) {
        super(capacity, maintenanceRecords, serviceStartDate);
        this.cilindrataMotore = cilindrataMotore;
    }

    public Bus(int capacity, LocalDate serviceStartDate, int cilindrataMotore) {
        super(capacity, serviceStartDate);
        this.cilindrataMotore = cilindrataMotore;
    }

    //GETTER AND SETTER

    public int getCilindrataMotore() {
        return cilindrataMotore;
    }

    public void setCilindrataMotore(int cilindrataMotore) {
        this.cilindrataMotore = cilindrataMotore;
    }

    @Override
        public String toString() {
            return "Bus{" +
                    "cilindrataMotore=" + cilindrataMotore +
                    " uuid=" + this.getUuid() +
                    '}';
        }
}
