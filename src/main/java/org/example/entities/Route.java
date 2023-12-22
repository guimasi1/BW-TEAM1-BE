package org.example.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue
    private UUID uuid;

    @Column(name = "departure_location")
    private String departureLocation;

    @Column(name = "arrival_location")
    private String arrivalLocation;

    @ManyToMany
    @JoinTable(name = "vehicles_routes", joinColumns = @JoinColumn(name = "routes_id"),
    inverseJoinColumns = @JoinColumn(name = "vehicles_id"))
    private List<Vehicle> vehicles;

    @Column(name = "average_travel_time")
    private Double averageTravelTime;

    @OneToMany(mappedBy = "route")
    private List<Trip> tripList;


    // CONSTRUCTORS

    public Route() {}
    public Route(String departureLocation, String arrivalLocation, Double averageTravelTime, Double elapsedTime) {
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.averageTravelTime = averageTravelTime;
    }


    // GETTER AND SETTER

    public UUID getUuid() {
        return uuid;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }
    public List<Vehicle> getVehicles() {
          return vehicles;
    }
    public void setVehicles(List<Vehicle> vehicles) {
          this.vehicles = vehicles;
    }

    public Double getAverageTravelTime() {
        return averageTravelTime;
    }

    public void setAverageTravelTime(Double averageTravelTime) {
        this.averageTravelTime = averageTravelTime;
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @Override
    public String toString() {
        return "Route{" +
                "uuid=" + uuid +
                ", departureLocation='" + departureLocation + '\'' +
                ", arrivalLocation='" + arrivalLocation + '\'' +
                ", vehicles=" + vehicles +
                ", averageTravelTime=" + averageTravelTime +
                ", tripList=" + tripList +
                '}';
    }
}