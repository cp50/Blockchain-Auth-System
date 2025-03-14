package com.projectjava.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor  // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor with all fields
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // ✅ Explicit getter and setter methods (even if Lombok is used)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ Matching constructor for user registration
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
