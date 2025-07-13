package com.data.javarest04.controller;

import com.data.javarest04.entity.Booking;
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

@Controller
@RequestMapping("/bookingFlights")
public class BookingFlightController {
    @Autowired
    private FlightService flightService;
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String findAll(Model model,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size,
                          @RequestParam(name = "phone", required = false) String customerPhone) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Booking> flightPage;

        if (customerPhone != null && !customerPhone.isEmpty()) {
            flightPage = bookingService.findByCustomerPhone(customerPhone, pageable);
        } else {
            flightPage = bookingService.getAll(pageable);
        }

        model.addAttribute("flightPage", flightPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("lastPage", flightPage.isLast());
        model.addAttribute("phone", customerPhone);
        return "bookingList";
    }
    @GetMapping("/newBooking")
    public String newBooking() {
        return "bookingForm";
    }
    @PostMapping("/newBooking")
    public String newBooking(@ModelAttribute("booking") Booking booking) {
        bookingService.add(booking);
        return "redirect:/bookingFlights";
    }
    @PostMapping("booking/cancel")
    public String cancelBooking(@RequestParam("bookingId") Long id) {
        System.out.println(id);
        Booking booking = bookingService.findById(id);
        if (booking != null && booking.isStatus()) {
            booking.setStatus(false);
            bookingService.add(booking);
        }
        return "redirect:/bookingFlights";
    }

}
