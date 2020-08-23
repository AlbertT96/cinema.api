package com.example.cinema.api.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Builder
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String name;
    private Long maxPlaces;
    @OneToMany(mappedBy = "showing",fetch = FetchType.EAGER)
    private Set<Reservation> reservations;
}
