package ra.edu.ss14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss14.model.dto.entity.Showtime;
import ra.edu.ss14.repository.ShowtimeRepository;

import java.util.List;

@Service
public class ShowtimeServiceImp implements ShowtimeService{
    @Autowired
    private ShowtimeRepository showtimeRepository;
    @Override
    public List<Showtime> getAll() {
        return showtimeRepository.findAll();
    }

    @Override
    public Showtime save(Showtime showtime) {
        return showtimeRepository.save(showtime);
    }
}
