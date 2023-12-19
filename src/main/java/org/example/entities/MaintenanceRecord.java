package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class MaintenanceRecord {

    @Column(name = "maintenance_start_date")
    private LocalDate maintenanceStartDate;

    @Column(name = "maintenance_end_date")
    private LocalDate maintenanceEndDate;


}
