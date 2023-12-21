package org.example.entities;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue
    private UUID uuid;
    private LocalDateTime startTime;
    private LocalDateTime arrivalTime;

    private Duration duration;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    public Trip() {}
    public Trip(LocalDateTime startTime, LocalDateTime arrivalTime, Vehicle vehicle, Route route) {
        this.startTime = startTime;
        this.arrivalTime = arrivalTime;
        this.vehicle = vehicle;
        this.route = route;
        this.duration = Duration.between(this.arrivalTime, this.startTime);
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "uuid=" + uuid +
                ", startTime=" + startTime +
                ", arrivalTime=" + arrivalTime +
                ", duration=" + duration +
                '}';
    }
}
