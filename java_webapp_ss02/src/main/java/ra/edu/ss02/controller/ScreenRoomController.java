package ra.edu.ss02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss02.entity.ScreenRoom;
import ra.edu.ss02.service.ScreenRoomService;
import ra.edu.ss02.service.TheaterService;


import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/screen-rooms")
public class ScreenRoomController {

    @Autowired
    private ScreenRoomService screenRoomService;

    @Autowired
    private TheaterService theaterService;

    @GetMapping
    public String getAll(Model model) {
        List<ScreenRoom> list = screenRoomService.findAll();
        model.addAttribute("screenRooms", list);
        return "screenroom/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("screenRoom", new ScreenRoom());
        model.addAttribute("theaters", theaterService.findAll());
        return "screenroom/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("screenRoom") ScreenRoom screenRoom) {
        screenRoomService.save(screenRoom);
        return "redirect:/screen-rooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<ScreenRoom> optional = screenRoomService.findById(id);
        if (optional.isPresent()) {
            model.addAttribute("screenRoom", optional.get());
            model.addAttribute("theaters", theaterService.findAll());
            return "screenroom/edit";
        }
        return "redirect:/screen-rooms";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("screenRoom") ScreenRoom screenRoom) {
        screenRoom.setId(id);
        screenRoomService.update(screenRoom);
        return "redirect:/screen-rooms";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        screenRoomService.delete(id);
        return "redirect:/screen-rooms";
    }
}
