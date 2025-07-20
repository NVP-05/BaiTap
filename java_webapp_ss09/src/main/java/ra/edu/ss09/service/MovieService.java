package ra.edu.ss09.service;

import ra.edu.ss09.model.entity.Movie;

import java.util.List;

public interface MovieService {
    Movie createMovie(Movie movie);

    Movie update(Long id, Movie updated);

    void delete(Long id);

    List<Movie> findAll();

    Movie findById(Long id);

    List<Movie> searchByKeyword(String keyword);
}
