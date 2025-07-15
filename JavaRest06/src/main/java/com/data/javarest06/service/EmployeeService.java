package com.data.javarest06.service;

import com.data.javarest06.model.entity.Employee;
import com.data.javarest06.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class EmployeeService implements IService<Employee,Long> {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
