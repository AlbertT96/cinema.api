package com.example.cinema.api.services;

import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.repository.ReservationRepository;
import com.example.cinema.api.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class ReservationServices {
    private final ReservationRepository reservationRepo;
    private final ShowingServices showingServices;

    Set<Reservation> getReservedPlacesByShowing(Showing showing) {
        return reservationRepo.findAllByShowing(showing);
    }

    void deleteReservationById(Long id) {
        reservationRepo.deleteById(id);
    }

    String addReservation(String firstName, String lastName, Long sitNumber, Long showingId) {
        if(showingServices.getAllAvailableSitByShowingId(showingId).stream().anyMatch(x-> x.equals(sitNumber))){
            Reservation reservation = Reservation.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .sitNumber(sitNumber)
                    .showing(Showing.builder().id(showingId).build())
                    .build();

            reservationRepo.save(reservation);
            return "success add";
        }
        return "fail";

    }
}
