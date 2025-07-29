package ra.edu.ss15.model.dto.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne private Product product;

    private int rating;
    private String comment;
    private LocalDateTime createdDate;
}

