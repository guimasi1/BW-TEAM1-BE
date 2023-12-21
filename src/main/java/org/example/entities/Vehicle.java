package org.example.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


    @Entity
    @Table(name = "vehicle")
    @Inheritance(strategy = InheritanceType.JOINED)
    @DiscriminatorColumn(name = "vehicle_type")
    @NamedQueries({
            @NamedQuery(name = "getMaintenanceRecordsByPeriod", query = "SELECT mr FROM Vehicle v JOIN v.maintenanceRecords mr "
                    + "WHERE v.uuid = :vehicleUUID "
                    + "AND (mr.maintenanceStartDate BETWEEN :startDate AND :endDate "
                    + "OR mr.maintenanceEndDate BETWEEN :startDate AND :endDate)"),
            @NamedQuery(name = "getRoutesByPeriod", query = "SELECT r FROM vehicle v JOIN v.routes r "
                    + "WHERE v.uuid = :vehicleUUID "
                    + "AND (r.serviceStartDate BETWEEN :startDate AND :endDate "
                    + "OR r.serviceEndDate BETWEEN :startDate AND :endDate)")
    })
    public abstract class Vehicle {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
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


        @ManyToMany(mappedBy = "vehicle")
        private List<Ticket> tickets;


        @ManyToMany
        @JoinTable(name = "vehicles_routes", joinColumns = @JoinColumn(name = "vehicles_id"),
                inverseJoinColumns = @JoinColumn(name = "routes_id"))
        private List<Route> routes;



        public Vehicle() {

        }

        public Vehicle(int capacity, LocalDate maintenanceStartDate, LocalDate maintenanceEndDate, LocalDate serviceStartDate, LocalDate serviceEndDate) {
            this.capacity = capacity;
            this.serviceStartDate = serviceStartDate;
            this.serviceEndDate = serviceEndDate;
        }

        public UUID getUUID() {
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
                    '}';
        }
    }

