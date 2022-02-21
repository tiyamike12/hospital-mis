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
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Patient firstname is required")
    private String firstname;
    @NotBlank(message = "Patient lastname is required")
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @NotBlank(message = "Patient phone is required")
    private String phone;
    private String email;
    @NotBlank(message = "Patient address is required")
    private String address;
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;
    @NotBlank(message = "Kin name is required")
    private String nextOfKinName;
    @NotBlank(message = "Kin phone is required")
    private String nextOfKinPhone;
    private Instant createdAt;
}
