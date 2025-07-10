package com.data.javarest03.repository;

import com.data.javarest03.dto.EmployeeDto;
import com.data.javarest03.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByPhoneNumber(String phone);
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findEmployeesWithSalaryGreaterThan(@Param("salary") Double salary);
    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    Page<Employee> findBySalaryGreaterThan(@Param("salary") double salary, Pageable pageable);
    @Query("SELECT new com.data.javarest03.dto.EmployeeDto(e.id, e.name, e.email, e.salary) FROM Employee e")
    List<EmployeeDto> findAllEmployeeDto();
    @Query("SELECT e.name AS name, e.phoneNumber AS phoneNumber, e.salary AS salary FROM Employee e")
    List<EmployeeInfo> findAllEmployeeInfo();
}
