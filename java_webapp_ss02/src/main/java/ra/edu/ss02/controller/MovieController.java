package ra.edu.ss02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss02.entity.Movie;
import ra.edu.ss02.service.MovieService;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping
    public String showMovie(Model model) {
        model.addAttribute("movies",movieService.findAll());
        return "listMovie";
    }
    @GetMapping("/add")
    public String addMovie(Model model) {
        model.addAttribute("movie",new Movie());
        return "addMovie";
    }
    @PostMapping("/add")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.save(movie);
        return "redirect:/movies";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Movie> movieOptional = movieService.findById(id);
        if (movieOptional.isPresent()) {
            model.addAttribute("movie", movieOptional.get());
            return "editMovie";
        } else {
            return "redirect:/movies";
        }
    }
    @PostMapping("/edit/{id}")
    public String updateMovie(@PathVariable Long id, @ModelAttribute("movie") Movie movie) {
        movie.setId(id);
        movieService.update(movie);
        return "redirect:/movies";
    }
    @GetMapping("/delete/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
        return "redirect:/movies";
    }
}
