package ra.edu.ss10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss10.model.entity.*;
import ra.edu.ss10.repository.*;

import java.util.UUID;

@Service
public class TransactionCreditServiceImp implements TransactionCreditService {
    @Autowired
    private TransactionCreditRepository transactionCreditRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public TransactionCredit spend(Long cardId, Long receiverId, Double money, String note) {
        var card = creditCardRepository.findById(cardId)
                .orElseThrow(() -> new RuntimeException("Credit card not found"));
        var receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        TransactionCredit tx = new TransactionCredit();
        tx.setCreditCardSender(card);
        tx.setAccountReceiver(receiver);
        tx.setMoney(money);
        tx.setNote(note);

        if (card.getAmountSpent() + money > card.getSpendingLimit()) {
            tx.setStatus("thất bại");
            transactionCreditRepository.save(tx);
            System.err.println("Chi tiêu vượt hạn mức: " + money);
            return tx;
        }

        try {
            card.setAmountSpent(card.getAmountSpent() + money);
            creditCardRepository.save(card);

            tx.setStatus("thành công");
            TransactionCredit saved = transactionCreditRepository.save(tx);

            // Notification
            Notification notiSender = new Notification();
            notiSender.setAccount(card.getAccount());
            notiSender.setContent("Bạn đã chi tiêu " + money + " VNĐ cho tài khoản " + receiver.getFullName());

            Notification notiReceiver = new Notification();
            notiReceiver.setAccount(receiver);
            notiReceiver.setContent("Bạn nhận được " + money + " VNĐ từ thẻ của " + card.getAccount().getFullName());

            notificationRepository.save(notiSender);
            notificationRepository.save(notiReceiver);

            return saved;
        } catch (Exception e) {
            tx.setStatus("thất bại");
            transactionCreditRepository.save(tx);
            System.err.println("Lỗi khi chi tiêu thẻ: " + e.getMessage());
            throw new RuntimeException("Transaction failed");
        }
    }
}
