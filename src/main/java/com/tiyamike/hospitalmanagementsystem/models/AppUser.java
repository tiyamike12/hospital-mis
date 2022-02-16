package com.tiyamike.hospitalmanagementsystem.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Username is required")
    private String username;
    @Email(message = "Invalid Email")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    @NotNull
    @Size(min = 4, max = 30)
    private String firstname;
    @NotNull
    @Size(min = 4, max = 30)
    private String lastname;
}
