package ra.edu.ss10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss10.model.dto.response.ApiDataResponse;
import ra.edu.ss10.model.entity.Transaction;
import ra.edu.ss10.service.TransactionService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiDataResponse<Transaction>> transfer(@RequestBody Map<String, Object> body) {
        Long senderId = (Long) body.get("senderId");
        Long receiverId = (Long) body.get("receiverId");
        Double money = Double.valueOf(body.get("money").toString());
        String note = (String) body.get("note");

        Transaction result = transactionService.save(senderId, receiverId, money, note);

        ApiDataResponse<Transaction> response = new ApiDataResponse<>(result, HttpStatus.CREATED);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
