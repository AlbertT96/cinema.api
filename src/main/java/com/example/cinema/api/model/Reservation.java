package com.example.cinema.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Setter
@Getter
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
