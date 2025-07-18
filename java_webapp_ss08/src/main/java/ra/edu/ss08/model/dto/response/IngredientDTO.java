package ra.edu.ss08.model.dto.response;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Data
public class IngredientDTO {
    private Long id;
    private String name;
    private Integer stock;
    private LocalDate expiry;
    private String image;
    private MultipartFile imageFile;
}
