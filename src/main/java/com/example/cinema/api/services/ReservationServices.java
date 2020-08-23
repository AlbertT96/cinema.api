package com.example.cinema.api.services;

import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.repository.ReservationRepository;
import com.example.cinema.api.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class ReservationServices {
    private final ReservationRepository reservationRepo;
    private final ShowingServices showingServices;

    public Set<Reservation> getReservedPlacesByShowing(Showing showing) {
        return reservationRepo.findAllByShowing(showing);
    }

    public void deleteReservationById(Long id) {
        reservationRepo.deleteById(id);
    }

    public Reservation getReservationById(Long id) {
        return reservationRepo.getOne(id);
    }

    public void addReservation(String firstName, String lastName, Long seatNumber, Long showingId) {
        if(showingServices.getAllAvailableSitByShowingId(showingId).stream().anyMatch(x-> ! x.equals(seatNumber))){
            Reservation reservation = Reservation.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .seatNumber(seatNumber)
                    .showing(Showing.builder().id(showingId).build())
                    .build();

            reservationRepo.save(reservation);

            log.info("add reservation");

        }

        log.warn("add reservation error");

    }

    public void putReservation(Long id, String firstName, String lastName, Long showingId, Long seatNumber) {
        if(showingServices.getAllAvailableSitByShowingId(showingId).stream().anyMatch(x-> ! x.equals(seatNumber))){
            Reservation reservation = Reservation.builder()
                    .id(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .seatNumber(seatNumber)
                    .showing(Showing.builder().id(showingId).build())
                    .build();

            reservationRepo.save(reservation);

            log.info("put reservation");
        }

        log.warn("put reservation error");
    }
}
