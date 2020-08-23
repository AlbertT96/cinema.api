package com.example.cinema.api.dto;

import com.example.cinema.api.model.Showing;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
@Getter
public class ReservationDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long sitNumber;
    private Long showingId;

}
