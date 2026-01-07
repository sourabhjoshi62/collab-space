package com.example.collab_space.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
public class Otp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Integer otp;

    @OneToOne
    User user;

    @CreationTimestamp
    LocalDateTime creationTime;

    @Column(nullable = false)
    LocalDateTime expiryTime;
}
