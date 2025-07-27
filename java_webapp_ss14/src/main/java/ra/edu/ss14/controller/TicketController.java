package ra.edu.ss14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss14.model.dto.entity.Ticket;
import ra.edu.ss14.model.dto.entity.User;
import ra.edu.ss14.security.pricipal.CustomUserDetails;
import ra.edu.ss14.service.TicketService;
import ra.edu.ss14.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @PostMapping("/tickets/book")
    public ResponseEntity<Ticket> bookTicket(@RequestParam Long showtimeId,
                                             @RequestParam String seatNumber,
                                             @RequestParam String price,
                                             @AuthenticationPrincipal CustomUserDetails principal) {
        User user = userService.getByUsername(principal.getUsername());
        Ticket ticket = ticketService.bookTicket(showtimeId, seatNumber, user, price);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/tickets/my")
    public ResponseEntity<List<Ticket>> getMyTickets(@AuthenticationPrincipal CustomUserDetails principal) {
        User user = userService.getByUsername(principal.getUsername());
        return ResponseEntity.ok(ticketService.getMyTickets(user));
    }

    @GetMapping("/admin/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
