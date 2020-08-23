package com.example.cinema.api.controller;

import com.example.cinema.api.dto.ReservationDto;
import com.example.cinema.api.model.Reservation;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.services.ReservationServices;
import com.example.cinema.api.services.ShowingServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
public class ReservationController {
    private final ShowingServices showingServices;
    private final ReservationServices reservationServices;

    @GetMapping("addReservation")
    String addReservation(Model model) {
        model.addAttribute("showingList", showingServices.getAllShowing());
        return "";
    }

    @PostMapping("addReservation")
    String addReservation(@RequestBody ReservationDto reservationDto) {
        reservationServices.addReservation(
                reservationDto.getFirstName(),
                reservationDto.getLastName(),
                reservationDto.getSitNumber(),
                reservationDto.getShowingId());
        return "";
    }

    @GetMapping("editReservation/{id}")
    String editReservation(@PathVariable Long id, Model model) {
        model.addAttribute("reservation", reservationServices.getReservationById(id));
        return "";
    }

    @PostMapping("editReservation/{id}")
    String putEditReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        reservationServices.putReservation(id,
                reservationDto.getFirstName(),
                reservationDto.getLastName(),
                reservationDto.getShowingId(),
                reservationDto.getSitNumber());
        return "";
    }

}
