package com.example.cinema.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Table(uniqueConstraints =
        @UniqueConstraint(columnNames={"sit_number", "showing_reservations"}))
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(name = "sit_number")
    private Long sitNumber;
    @ManyToOne
    @JoinColumn(name="showing_reservations")
    private Showing showing;
}
