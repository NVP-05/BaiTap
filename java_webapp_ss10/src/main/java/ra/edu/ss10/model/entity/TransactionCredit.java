package ra.edu.ss10.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TransactionCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Account accountReceiver;

    @ManyToOne
    private CreditCard creditCardSender;

    private String note;
    private Double money;
    private String status;
}