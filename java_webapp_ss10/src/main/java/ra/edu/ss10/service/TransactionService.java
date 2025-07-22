package ra.edu.ss10.service;

import ra.edu.ss10.model.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction findById(Long id);
    Transaction save(Long senderId, Long receiverId, Double money, String note);

}
