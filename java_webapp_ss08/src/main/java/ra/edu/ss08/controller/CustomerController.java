package ra.edu.ss08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss08.model.dto.response.ApiResponse;
import ra.edu.ss08.model.dto.response.CustomerDTO;
import ra.edu.ss08.model.entity.Customer;
import ra.edu.ss08.service.CustomerService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDTO>>> findAll() {
        List<CustomerDTO> result = customerService.getAllCustomers()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(result, true, "Lấy danh sách khách hàng thành công"));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDTO>> createCustomer(CustomerDTO dto) {
        Customer saved = customerService.createCustomer(toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(saved), true, "Thêm khách hàng thành công"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDTO>> updateCustomer(@PathVariable Long id, CustomerDTO dto) {
        Customer updated = customerService.updateCustomer(id, toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(updated), true, "Cập nhật khách hàng thành công"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(new ApiResponse<>(null, true, "Xóa khách hàng thành công"));
    }
    private Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFullName(dto.getFullName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setNumberOfPayments(dto.getNumberOfPayments());
        customer.setStatus(dto.isStatus());
        customer.setCreatedAt(dto.getCreatedAt());
        return customer;
    }
    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFullName(customer.getFullName());
        dto.setPhone(customer.getPhone());
        dto.setEmail(customer.getEmail());
        dto.setNumberOfPayments(customer.getNumberOfPayments());
        dto.setStatus(customer.isStatus());
        dto.setCreatedAt(customer.getCreatedAt() != null ? LocalDate.parse(customer.getCreatedAt().toString()) : null);
        return dto;
    }

}
