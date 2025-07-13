package com.data.javarest04.controller;

import com.data.javarest04.entity.Booking;
import com.data.javarest04.entity.Flight;
import com.data.javarest04.service.BookingService;
import com.data.javarest04.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private BookingService bookingService;
    @GetMapping("flights")
    public String getAllFlights(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String departure,
            @RequestParam(required = false) String destination
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Flight> flightPage;

        if (departure != null && !departure.isEmpty() || destination != null && !destination.isEmpty()) {
            flightPage = flightService.searchFlights(departure, destination, pageable);
        } else {
            flightPage = flightService.getAll(pageable);
        }

        model.addAttribute("flightPage", flightPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", flightPage.isLast());
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);

        return "flightList";
    }
    @GetMapping("/booking/new")
    public String newBooking(@RequestParam("flightId") Long id, Model model) {
        Flight flight = flightService.findById(id);
        model.addAttribute("flight", flight);
        return "bookingForm";
    }
    @PostMapping("/booking/new")
    public String createBooking(@RequestParam Long flightId,
                                @RequestParam String customerName,
                                @RequestParam String customerPhone,
                                Model model) {
        Flight flight = flightService.findById(flightId);
        Booking booking = new Booking();
        booking.setFlight(flight);
        booking.setCustomerName(customerName);
        booking.setCustomerPhone(customerPhone);
        booking.setBookingTime(LocalDateTime.now());
        booking.setStatus(true);
        bookingService.add(booking);
        model.addAttribute("message", "Đặt vé thành công!");
        return "redirect:/flights";
    }

}
