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
        @Column(name = "type")
        private String type;
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


        public Vehicle() {

        }

        public Vehicle(String type, int capacity, LocalDate maintenanceStartDate, LocalDate maintenanceEndDate, LocalDate serviceStartDate, LocalDate serviceEndDate) {
            this.type = type;
            this.capacity = capacity;
            this.serviceStartDate = serviceStartDate;
            this.serviceEndDate = serviceEndDate;
        }

        public UUID getUUID() {
            return uuid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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









//        inizio metodi vincenzo



        public void vidimaTicket(Ticket ticket) {
            if (isTicketValid(ticket)) {
                ticket.setAnnullato(true);
                // Puoi aggiungere ulteriori logiche qui, ad esempio, salvare il ticket annullato in una lista
            } else {
                System.out.println("Ticket timbrato e annullato");
            }
        }


        private boolean isTicketValid(Ticket ticket) {
            if (ticket == null || ticket.isAnnullato()) {
                return false; // Se il ticket è nullo o già annullato, non è valido
            }

            LocalDate dataVidimazione = ticket.getDataDiVidimazione();
            if (dataVidimazione == null) {
                return false; // Se la data di vidimazione è nulla, il ticket non è valido
            }

            // Alcuni esempi di criteri che potresti considerare:
            // 1. Verifica se il veicolo associato al ticket è di un tipo specifico
            // 2. Verifica se il ticket è ancora valido rispetto alla sua data di scadenza
            // 3. Altri criteri specifici del tuo dominio

            // Esempio: Verifica se il veicolo è in servizio alla data di vidimazione del ticket
            LocalDate serviceStartDate = getServiceStartDate();
            LocalDate serviceEndDate = getServiceEndDate();

            if (serviceStartDate == null || serviceEndDate == null) {
                return false; // Se le date di servizio del veicolo sono nulle, il ticket non è valido
            }

            // Verifica se la data di vidimazione del ticket è compresa tra la data di inizio e fine del servizio del veicolo
            return dataVidimazione.isAfter(serviceStartDate) && dataVidimazione.isBefore(serviceEndDate.plusDays(1));
        }




        public int getNumeroBigliettiVidimati() {
            if (tickets == null) {
                return 0;
            }
            return (int) tickets.stream().filter(Ticket::isAnnullato).count();
        }

        public int getNumeroBigliettiVidimati(LocalDate startDate, LocalDate endDate) {
            if (tickets == null) {
                return 0;
            }
            return (int) tickets.stream()
                    .filter(ticket -> ticket.isAnnullato() && isTicketVidimatoInRange(ticket, startDate, endDate))
                    .count();
        }

        private boolean isTicketVidimatoInRange(Ticket ticket, LocalDate startDate, LocalDate endDate) {
            LocalDate dataVidimazione = ticket.getDataDiVidimazione();
            return dataVidimazione.isAfter(startDate) && dataVidimazione.isBefore(endDate.plusDays(1));
        }
//fine metodi vincenzo


















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
                    ", type='" + type + '\'' +
                    ", capacity=" + capacity +
                    ", maintenanceRecords=" + maintenanceRecords +
                    ", serviceStartDate=" + serviceStartDate +
                    ", serviceEndDate=" + serviceEndDate +
                    ", tickets=" + tickets +
                    ", routes=" + routes +
                    '}';
        }
    }

