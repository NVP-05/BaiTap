package ra.edu.ss02.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;
    @ManyToOne
    @JoinColumn(name = "screen_room_id")
    private ScreenRoom screenRoom;
}

