package ra.edu.ss14.service;

import ra.edu.ss14.model.dto.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAll();
    Movie save(Movie movie);
    Movie update(Long id, Movie movie);
    void delete(Long id);
}
