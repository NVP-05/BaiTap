package ra.edu.ss08.model.dto.response;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String fullname;
    private String phone;
    private String address;
    private String position;
    private Double salary;
}
