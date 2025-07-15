package com.data.javarest06.controller;

import com.data.javarest06.model.entity.User;
import com.data.javarest06.model.response.DataResponse;
import com.data.javarest06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> findAll() {
        return new ResponseEntity<>(
                new DataResponse<>(userService.findAll(), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> save(@RequestBody User user) {
        return new ResponseEntity<>(
                new DataResponse<>(userService.save(user), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> update(@RequestBody User user) {
        return new ResponseEntity<>(
                new DataResponse<>(userService.update(user), HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<User>> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(
                new DataResponse<>(null, HttpStatus.OK),
                HttpStatus.OK
        );
    }
}
