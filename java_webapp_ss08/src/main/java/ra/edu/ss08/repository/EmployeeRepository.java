package ra.edu.ss08.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.edu.ss08.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}