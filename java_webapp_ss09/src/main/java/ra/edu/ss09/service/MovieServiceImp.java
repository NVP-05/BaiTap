package ra.edu.ss09.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss09.model.entity.Movie;
import ra.edu.ss09.repository.MovieRepository;

import java.util.List;

@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie updated) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại với ID: " + id));
        movie.setId(id);
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Long id) {Movie movie = movieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Phim không tồn tại với ID: " + id));
        movieRepository.delete(movie);

    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phim không tồn tại với ID: " + id));
    }

    @Override
    public List<Movie> searchByKeyword(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return movieRepository.findAll();
        }
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }
}
