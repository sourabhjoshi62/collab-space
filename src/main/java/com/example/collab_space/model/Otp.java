package com.example.collab_space.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Entity
@Data
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer otp;

    @ManyToOne
    User user;

    @Column(nullable = false)
    LocalTime creationTime;

    @Column(nullable = false)
    LocalTime expiryTime;
}
