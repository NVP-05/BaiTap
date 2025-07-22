package ra.edu.ss10.service;

import ra.edu.ss10.model.entity.TransactionCredit;

public interface TransactionCreditService {
    TransactionCredit spend(Long cardId, Long receiverId, Double money, String note);
}
