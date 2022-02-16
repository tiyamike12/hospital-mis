package com.tiyamike.hospitalmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nurses")
public class Nurse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "First name is required")
    private String firstname;
    @NotNull(message = "Last name is required")
    private String lastname;
    private String phone;
    @Email
    private String email;
    @NotNull(message = "Qualification is required")
    private String qualification;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateJoined;
    private long departmentId;
    @ManyToOne
    @JoinColumn(name = "departmentId", insertable = false, updatable = false)
    private Department department;
    private boolean isActive;
    private Instant createdAt;
}
