package ra.edu.ss14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss14.model.dto.entity.*;
import ra.edu.ss14.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TicketServiceImp implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowtimeRepository showtimeRepository;

    @Override
    public Ticket bookTicket(Long showtimeId, String seatNumber, User user, String price) {
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new NoSuchElementException("Showtime not found"));

        Ticket ticket = Ticket.builder()
                .user(user)
                .showtime(showtime)
                .seatNumber(seatNumber)
                .bookingTime(LocalDateTime.now())
                .price(new BigDecimal(price))
                .build();

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getMyTickets(User user) {
        return ticketRepository.findByUser(user);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
