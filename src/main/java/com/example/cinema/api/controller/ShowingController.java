package com.example.cinema.api.controller;

import com.example.cinema.api.dto.ReservationDto;
import com.example.cinema.api.model.Showing;
import com.example.cinema.api.services.ReservationServices;
import com.example.cinema.api.services.ShowingServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Controller
public class ShowingController {
    private final ShowingServices showingServices;
    private final ReservationServices reservationServices;

    @GetMapping("addShowing")
    String addShowing(Model model) {
        return "";
    }

    @PostMapping("addShowing")
    String addShowing(@RequestBody Showing showing) {
        showingServices.addShowing(
                showing.getDate(),
                showing.getMaxPlaces(),
                showing.getName());
        return "";
    }

    @GetMapping("listShowing")
    String editShowing(Model model) {
        model.addAttribute("showing", showingServices.getAllShowing());
        return "";
    }

    @PostMapping("deleteShowing/{id}")
    String deleteShowing(@PathVariable Long id) {
        showingServices.deleteShowingById(id);
        return "";
    }
}
