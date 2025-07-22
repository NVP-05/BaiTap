package ra.edu.ss11.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}
