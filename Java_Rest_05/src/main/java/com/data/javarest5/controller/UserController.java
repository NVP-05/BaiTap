package com.data.javarest5.controller;

import com.data.javarest5.model.entity.User;
import com.data.javarest5.model.response.DataResponse;
import com.data.javarest5.service.UserService;
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
    public ResponseEntity<DataResponse<List<User>>> getAllUsers() {
        return new ResponseEntity<>(new DataResponse<>(userService.getAllUsers(), HttpStatus.OK), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DataResponse<User>> createUser(@RequestBody User user) {
        return new ResponseEntity<>(new DataResponse<>(userService.createUser(user), HttpStatus.CREATED), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(new DataResponse<>(userService.updateUser(id, user), HttpStatus.OK), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<User>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(new DataResponse<>(null, HttpStatus.OK), HttpStatus.OK);
    }
}
