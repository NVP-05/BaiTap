package ra.edu.ss10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss10.model.dto.response.ApiDataResponse;
import ra.edu.ss10.model.entity.Notification;
import ra.edu.ss10.service.NotificationService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{accountId}")
    public ResponseEntity<ApiDataResponse<List<Notification>>> getByAccount(@PathVariable Long accountId) {
       return new ResponseEntity<>(new ApiDataResponse<>(notificationService.getByAccount(accountId), HttpStatus.OK), HttpStatus.OK);
    }
}
