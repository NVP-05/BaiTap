package ra.edu.ss09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/log")
public class StructuredLogController {

    @GetMapping("/event")
    public Map<String, Object> logEvent(@RequestParam String user, @RequestParam String action) {
        Map<String, Object> logData = new HashMap<>();
        logData.put("event", "user_action");
        logData.put("user", user);
        logData.put("action", action);
        logData.put("timestamp", System.currentTimeMillis());

        log.info("User event: {}", logData);

        return logData;
    }
}
