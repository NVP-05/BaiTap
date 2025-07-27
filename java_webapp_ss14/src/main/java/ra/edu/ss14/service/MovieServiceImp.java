package ra.edu.ss14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss14.model.dto.entity.Movie;
import ra.edu.ss14.repository.MovieRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieServiceImp implements MovieService{
    @Autowired
    private MovieRepository movieRepository;
    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie mov = movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found with id: " + id));
        mov.setId(id);
        return movieRepository.save(movie);
    }

    @Override
    public void delete(Long id) {
        Movie mov = movieRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Movie not found with id: " + id));
        movieRepository.delete(mov);
    }
}
