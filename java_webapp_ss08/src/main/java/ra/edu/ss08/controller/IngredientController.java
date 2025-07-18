package ra.edu.ss08.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss08.model.dto.response.ApiResponse;
import ra.edu.ss08.model.dto.response.IngredientDTO;
import ra.edu.ss08.model.entity.Ingredient;
import ra.edu.ss08.service.IngredientService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private Cloudinary cloudinary;
    @GetMapping
    public ResponseEntity<ApiResponse<List<IngredientDTO>>> findAll(){
        List<IngredientDTO> result = ingredientService.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
        return ResponseEntity.ok(new ApiResponse<>(result, true, "Lấy danh sách nguyên liệu thành công"));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<IngredientDTO>> createIngredient(@ModelAttribute IngredientDTO dto) {
        String imageUrl = null;
        try {
            if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(dto.getImageFile().getBytes(), ObjectUtils.emptyMap());
                imageUrl = uploadResult.get("secure_url").toString();
                dto.setImage(imageUrl);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(null, false, "Lỗi upload ảnh"));
        }

        Ingredient saved = ingredientService.create(toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(saved), true, "Thêm nguyên liệu thành công"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientDTO>> updateIngredient(@PathVariable Long id, @ModelAttribute IngredientDTO dto) {
        String imageUrl = null;
        try {
            if (dto.getImageFile() != null && !dto.getImageFile().isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(dto.getImageFile().getBytes(), ObjectUtils.emptyMap());
                imageUrl = uploadResult.get("secure_url").toString();
                dto.setImage(imageUrl);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(new ApiResponse<>(null, false, "Lỗi upload ảnh"));
        }

        Ingredient updated = ingredientService.update(id, toEntity(dto));
        return ResponseEntity.ok(new ApiResponse<>(toDTO(updated), true, "Cập nhật nguyên liệu thành công"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(null,true, "Xóa nguyên liệu thành công"));
    }

    private Ingredient toEntity(IngredientDTO dto) {
        Ingredient i = new Ingredient();
        i.setId(dto.getId());
        i.setName(dto.getName());
        i.setStock(dto.getStock());
        i.setExpiry(dto.getExpiry());
        i.setImage(dto.getImage());
        return i;
    }

    private IngredientDTO toDTO(Ingredient d) {
        IngredientDTO dto = new IngredientDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setStock(d.getStock());
        dto.setExpiry(d.getExpiry());
        dto.setImage(d.getImage());
        return dto;
    }
}
