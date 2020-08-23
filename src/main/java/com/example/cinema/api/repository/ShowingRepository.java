package com.example.cinema.api.repository;

import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ShowingRepository extends JpaRepository<Showing, Long> {

}
