package ra.edu.ss02.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss02.entity.Theater;
import ra.edu.ss02.service.TheaterService;

@Controller
@RequestMapping("/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("theaters", theaterService.findAll());
        return "theater/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("theater", new Theater());
        return "theater/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Theater theater) {
        theaterService.save(theater);
        return "redirect:/theaters";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Theater theater = theaterService.findById(id).orElseThrow();
        model.addAttribute("theater", theater);
        return "theater/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute Theater theater) {
        theaterService.update(theater);
        return "redirect:/theaters";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        theaterService.delete(id);
        return "redirect:/theaters";
    }
}
