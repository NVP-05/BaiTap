package ra.edu.ss02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss02.entity.Schedule;
import ra.edu.ss02.service.MovieService;
import ra.edu.ss02.service.ScheduleService;
import ra.edu.ss02.service.ScreenRoomService;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/showtimes")
public class ShowtimeController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ScreenRoomService screenRoomService;

    @GetMapping
    public String getAllShowtimes(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) Long screenRoomId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Model model
    ) {
        List<Schedule> schedules = scheduleService.findAll(); // default

        // Apply filters
        if (movieId != null) {
            schedules = schedules.stream()
                    .filter(s -> s.getMovie().getId().equals(movieId))
                    .toList();
        }

        if (screenRoomId != null) {
            schedules = schedules.stream()
                    .filter(s -> s.getScreenRoom().getId().equals(screenRoomId))
                    .toList();
        }

        if (date != null) {
            schedules = schedules.stream()
                    .filter(s -> s.getStartTime().toLocalDate().isEqual(date))
                    .toList();
        }

        model.addAttribute("schedules", schedules);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);
        model.addAttribute("selectedDate", date);
        return "listShowtime";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "addShowtime";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Schedule schedule) {
        schedule.setMovie(movieService.findById(schedule.getMovie().getId()).orElse(null));
        schedule.setScreenRoom(screenRoomService.findById(schedule.getScreenRoom().getId()).orElse(null));
        scheduleService.save(schedule);
        return "redirect:/showtimes";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Schedule> scheduleOpt = scheduleService.findById(id);
        if (scheduleOpt.isPresent()) {
            model.addAttribute("schedule", scheduleOpt.get());
            model.addAttribute("movies", movieService.findAll());
            model.addAttribute("screenRooms", screenRoomService.findAll());
            return "editShowtime";
        }
        return "redirect:/showtimes";
    }

    @PostMapping("/edit/{id}")
    public String updateSchedule(@PathVariable Long id, @ModelAttribute("schedule") Schedule schedule) {
        schedule.setId(id);
        scheduleService.update(schedule);
        return "redirect:/showtimes";
    }

    @PostMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/showtimes";
    }
}
