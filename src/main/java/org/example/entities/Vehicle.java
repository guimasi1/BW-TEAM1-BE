package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


    @Entity
    @Table(name = "vehicle")
    @Inheritance(strategy = InheritanceType.JOINED)
    @DiscriminatorColumn(name = "vehicle_type")
    public abstract class Vehicle {
        @Id
        @GeneratedValue
        private UUID uuid;
        @Column(name = "capacity")
        private int capacity;

      @ElementCollection
        @CollectionTable(
                name = "maintenance_records",
                joinColumns = @JoinColumn(name = "transport_means_id")
        )
        @OrderColumn(name = "maintenance_order")
        private List<MaintenanceRecord> maintenanceRecords;


        @Column(name = "service_start_date")
        private LocalDate serviceStartDate;
        @Column(name = "service_end_date")
        private LocalDate serviceEndDate;


        @ManyToMany
        @JoinTable(name = "vehicles_ticket", joinColumns = @JoinColumn(name = "vehicle"), inverseJoinColumns = @JoinColumn(name = "tickets"))
        private List<Ticket> tickets;


        @ManyToMany
        @JoinTable(name = "vehicles_routes", joinColumns = @JoinColumn(name = "vehicles_id"),
                inverseJoinColumns = @JoinColumn(name = "routes_id"))
        private List<Route> routes;

        @OneToMany(mappedBy = "vehicle")
        private List<Trip> tripList;

        public Vehicle() {}

        public Vehicle(int capacity, List<MaintenanceRecord> maintenanceRecords, LocalDate serviceStartDate) {
            this.capacity = capacity;
            this.maintenanceRecords = maintenanceRecords;
            this.serviceStartDate = serviceStartDate;
        }
        public Vehicle(int capacity, LocalDate serviceStartDate) {
            this.capacity = capacity;
            this.serviceStartDate = serviceStartDate;
        }



        public UUID getUuid() {
            return uuid;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public List<MaintenanceRecord> getMaintenanceRecords() {
            return maintenanceRecords;
        }

        public void setMaintenanceRecords(List<MaintenanceRecord> maintenanceRecords) {
            this.maintenanceRecords = maintenanceRecords;
        }

        public LocalDate getServiceStartDate() {
            return serviceStartDate;
        }

        public void setServiceStartDate(LocalDate serviceStartDate) {
            this.serviceStartDate = serviceStartDate;
        }

        public LocalDate getServiceEndDate() {
            return serviceEndDate;
        }

        public void setServiceEndDate(LocalDate serviceEndDate) {
            this.serviceEndDate = serviceEndDate;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        public List<Route> getRoutes() {
            return routes;
        }

        public void setRoutes(List<Route> routes) {
            this.routes = routes;
        }

        public List<Trip> getTripList() {
            return tripList;
        }

        public void setTripList(List<Trip> tripList) {
            this.tripList = tripList;
        }


        @Override
        public String toString() {
            return "Vehicle{" +
                    "uuid=" + uuid +
                    ", capacity=" + capacity +
                    ", maintenanceRecords=" + maintenanceRecords +
                    ", serviceStartDate=" + serviceStartDate +
                    ", serviceEndDate=" + serviceEndDate +
                    ", tickets=" + tickets +
                    ", routes=" + routes +
                    ", tripList=" + tripList +
                    '}';
        }
    }

