package org.example.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

    @Entity
    @Table(name = "trams")
    @DiscriminatorValue("Tram")
    public class Tram extends Vehicle{
        @Column(name = "numero_vagoni")
        private int numeroVagoni;

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
