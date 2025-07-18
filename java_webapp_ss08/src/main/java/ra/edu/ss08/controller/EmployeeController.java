package ra.edu.ss08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss08.model.dto.response.ApiResponse;
import ra.edu.ss08.model.dto.response.EmployeeDTO;
import ra.edu.ss08.model.entity.Employee;
import ra.edu.ss08.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> result = employeeService.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(result, true, "Lấy danh sách nhân viên"));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> createEmployee(EmployeeDTO dto) {
        Employee saved = employeeService.create(toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(saved), true, "Thêm nhân viên thành công"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(@PathVariable Long id, EmployeeDTO dto) {
        Employee updated = employeeService.update(id, toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(updated), true, "Cập nhật nhân viên thành công"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(null, true, "Xóa nhân viên thành công"));
    }
    private Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setFullname(dto.getFullname());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        return employee;
    }
    private EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFullname(employee.getFullname());
        dto.setPhone(employee.getPhone());
        dto.setAddress(employee.getAddress());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        return dto;
    }
}
