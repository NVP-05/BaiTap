package ra.edu.ss10.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account sender;

    @ManyToOne
    private Account receiver;

    private Double money;
    private String note;
    private String status;
    private LocalDateTime createdAt;
}
