package ra.edu.ss15.model.dto.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String addressIp;

    @Column(nullable = false, unique = true)
    private String token;

    private LocalDateTime expiryDate;
}

