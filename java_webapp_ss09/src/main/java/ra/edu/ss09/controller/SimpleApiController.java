package ra.edu.ss09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class SimpleApiController {

    @GetMapping("/divide")
    public String divide(@RequestParam int a, @RequestParam int b) {
        int result = a / b;
        return "Kết quả: " + result;
    }
}