package ra.edu.ss14.service;

import ra.edu.ss14.model.dto.entity.Showtime;

import java.util.List;

public interface ShowtimeService {
    List<Showtime> getAll();
    Showtime save(Showtime showtime);
}
