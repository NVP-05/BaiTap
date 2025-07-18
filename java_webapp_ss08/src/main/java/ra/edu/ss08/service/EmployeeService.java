package ra.edu.ss08.service;

import ra.edu.ss08.model.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(Long id);
    Employee create(Employee employee);
    Employee update(Long id, Employee updated);
    void delete(Long id);
}
