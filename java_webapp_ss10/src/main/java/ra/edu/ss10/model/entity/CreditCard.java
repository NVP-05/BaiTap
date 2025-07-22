package ra.edu.ss10.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;


@Entity
@Data
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull(message = "Account không được để trống")
    private Account account;

    @NotNull(message = "Hạn mức không được để trống")
    @Positive(message = "Hạn mức phải lớn hơn 0")
    private Double spendingLimit;

    @NotNull(message = "Số tiền đã dùng không được để trống")
    @Min(value = 0, message = "Số tiền đã dùng không được âm")
    private Double amountSpent;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}
