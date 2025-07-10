package com.data.javarest03.controller;

import com.data.javarest03.entity.Employee;
import com.data.javarest03.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public String findAll(Model model, @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size, Sort.by("salary").ascending());
        Page<Employee> employeePage = employeeService.findAll((org.springframework.data.domain.Pageable) pageable);
        model.addAttribute("employeePage", employeePage);
        return "employees";
    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "addNewStudent";
    }
    @PostMapping("/add")
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees";
    }
    @GetMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        return "editStudent";
    }
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("employee") Employee employee) {
        employeeService.update(employee);
        return "redirect:/employees";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
        return "redirect:/employees";
    }
}
