package com.example.cinema.api.services;

import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.repository.ReservationRepository;
import com.example.cinema.api.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@AllArgsConstructor
@Service
public class ShowingServices {
    private final ShowingRepository showingRepo;
    private final ReservationRepository reservationRepo;

    public Set<Reservation> getReservedPlacesByShowing(Showing showing) {
        return reservationRepo.findAllByShowing(showing);
    }

    public List<Showing> getAllShowing() {
        return showingRepo.findAll();
    }
    
    public List<Long> getAllAvailableSitByShowingId(Long id) {
        Showing showing = showingRepo.getOne(id);
        Long maxPlaces = showing.getMaxPlaces();
        List<Long> reservedPlaces = getReservedPlacesByShowing(showing).stream()
                .map(Reservation::getSeatNumber)
                .collect(Collectors.toList());
        return LongStream.range(1, maxPlaces)
                .filter(x -> !reservedPlaces.contains(x))
                .boxed()
                .collect(Collectors.toList());
    }

    public Showing getShowingById(Long id) {
        return showingRepo.getOne(id);
    }

    public void addShowing(LocalDateTime date, Long maxPlaces, String name) {
        showingRepo.save(Showing.builder().date(date).maxPlaces(maxPlaces).name(name).build());
    }

    public void deleteShowingById(Long id) {
        showingRepo.deleteById(id);
    }
}
