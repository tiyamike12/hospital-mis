package com.tiyamike.hospitalmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "First name is required")
    private String firstname;
    @NotNull(message = "Last name is required")
    private String lastname;
    @Email
    private String email;
    private String phone;
    @NotNull(message = "Qualification is required")
    private String qualification;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateJoined;
    private long departmentId;
    @ManyToOne
    @JoinColumn(name = "departmentId", insertable = false, updatable = false)
    private Department department;
    private Boolean isActive;
    private Instant createdAt;
}
