package ra.edu.ss02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Bai1")
public class Bai1 {
    @GetMapping
    public String Bai1() {
        return "Bai1";
    }
}
