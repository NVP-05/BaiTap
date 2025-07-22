package ra.edu.ss10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss10.model.entity.TransactionCredit;
import ra.edu.ss10.service.TransactionCreditService;

import java.util.UUID;

@RestController
@RequestMapping("/credit-transactions")
public class TransactionCreditController {
    @Autowired
    private TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> spend(
            @RequestParam Long cardId,
            @RequestParam Long receiverId,
            @RequestParam Double money,
            @RequestParam String note
    ) {
        TransactionCredit tx = transactionCreditService.spend(cardId, receiverId, money, note);
        return ResponseEntity.status(201).body(tx);
    }
}
