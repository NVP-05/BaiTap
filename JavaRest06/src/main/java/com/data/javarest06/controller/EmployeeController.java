package com.data.javarest06.controller;

import com.data.javarest06.model.entity.Employee;
import com.data.javarest06.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(employee), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.save(employee), HttpStatus.OK);
    }
}
