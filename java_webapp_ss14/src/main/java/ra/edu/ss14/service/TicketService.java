package ra.edu.ss14.service;

import ra.edu.ss14.model.dto.entity.Ticket;
import ra.edu.ss14.model.dto.entity.User;

import java.util.List;

public interface TicketService {
    Ticket bookTicket(Long showtimeId, String seatNumber, User user, String price);
    List<Ticket> getMyTickets(User user);
    List<Ticket> getAllTickets();
}
