package com.example.cinema.api.services;

import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.repository.ShowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@AllArgsConstructor
@Service
public class ShowingServices {
    private final ShowingRepository showingRepo;
    private final ReservationServices reservationServices;

    List<Showing> getAllShowing() {
        return showingRepo.findAll();
    }
    
    List<Long> getAllAvailableSitByShowingId(Long id) {
        Showing showing = showingRepo.getOne(id);
        Long maxPlaces = showing.getMaxPlaces();
        List<Long> reservedPlaces = reservationServices.getReservedPlacesByShowing(showing).stream()
                .map(Reservation::getSitNumber)
                .collect(Collectors.toList());
        return LongStream.range(1, maxPlaces)
                .filter(x -> !reservedPlaces.contains(x))
                .boxed()
                .collect(Collectors.toList());
    }
    
    Showing getShowingById(Long id) {
        return showingRepo.getOne(id);
    }

    void addShowing(LocalDateTime date, Long maxPlaces, String name) {
        showingRepo.save(Showing.builder().date(date).maxPlaces(maxPlaces).name(name).build());
    }
    
    void deleteShowingById(Long id) {
        showingRepo.deleteById(id);
    }
}
