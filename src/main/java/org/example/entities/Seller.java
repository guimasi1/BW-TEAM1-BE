package org.example.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Seller {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "punto_di_emissione")
    private String puntoDiEmissione;
    @Enumerated(EnumType.STRING)
    @Column(name = "seller_type")
    private SellerType sellerType;
    @Column(name = "service_type")
    private Service serviceType;
    @OneToMany(mappedBy = "seller")
    @Column(name = "control_managment_list")
    private List<ControlManagement> controlManagementList;

    public Seller() {}

    public Seller(String puntoDiEmissione, SellerType sellerType, Service serviceType) {
        this.puntoDiEmissione = puntoDiEmissione;
        this.sellerType = sellerType;
        this.serviceType = serviceType;
        this.controlManagementList = controlManagementList;
    }

    public UUID getId() {
        return id;
    }

    public String getPuntoDiEmissione() {
        return puntoDiEmissione;
    }

    public void setPuntoDiEmissione(String puntoDiEmissione) {
        this.puntoDiEmissione = puntoDiEmissione;
    }

    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }

    public Service getServiceType() {
        return serviceType;
    }

    public void setServiceType(Service serviceType) {
        this.serviceType = serviceType;
    }

    public List<ControlManagement> getControlManagementList() {
        return controlManagementList;
    }

    public void setControlManagementList(List<ControlManagement> controlManagementList) {
        this.controlManagementList = controlManagementList;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", puntoDiEmissione='" + puntoDiEmissione + '\'' +
                ", sellerType=" + sellerType +
                ", serviceType=" + serviceType +
                ", controlManagementList=" + controlManagementList +
                '}';
    }
}

