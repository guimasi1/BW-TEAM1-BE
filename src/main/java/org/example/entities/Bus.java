package org.example.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
    @Table(name = "bus")
    @DiscriminatorValue("Bus")
    public class Bus extends Vehicle{
        @Column(name = "cilindrata_motore")
        private int cilindrataMotore;

    public Bus( int capacity, LocalDate maintenanceStartDate, LocalDate maintenanceEndDate, LocalDate serviceStartDate, LocalDate serviceEndDate, int cilindrataMotore) {
        super(capacity, maintenanceStartDate, maintenanceEndDate, serviceStartDate, serviceEndDate);
        this.cilindrataMotore = cilindrataMotore;
    }

    public Bus(){

        }

        public int getCilindrataMotore() {
            return cilindrataMotore;
        }

        public void setCilindrataMotore(int cilindrataMotore) {
            this.cilindrataMotore = org.example.entities.Bus.this.cilindrataMotore;
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "cilindrataMotore=" + cilindrataMotore +
                    '}';
        }
    }
