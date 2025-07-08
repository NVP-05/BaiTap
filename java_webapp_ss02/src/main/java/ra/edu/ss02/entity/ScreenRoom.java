package ra.edu.ss02.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScreenRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String capacity;
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;
    @OneToMany(mappedBy = "screenRoom")
    private List<Seat> seats;

    @OneToMany(mappedBy = "screenRoom")
    private List<Schedule> schedules;
}
