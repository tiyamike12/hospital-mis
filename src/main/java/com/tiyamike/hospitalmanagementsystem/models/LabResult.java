package com.tiyamike.hospitalmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lab_results")
public class LabResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Test name is required")
    private String testName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date testDate;
    @NotBlank(message = "Conducted By is required")
    private String conductedBy;
    private long patientId;
    @ManyToOne
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    private Patient patient;
    private Boolean result;
    private Instant createdAt;
}
