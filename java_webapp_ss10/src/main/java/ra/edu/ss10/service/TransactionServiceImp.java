package ra.edu.ss10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss10.model.entity.Account;
import ra.edu.ss10.model.entity.Notification;
import ra.edu.ss10.model.entity.Transaction;
import ra.edu.ss10.repository.AccountRepository;
import ra.edu.ss10.repository.NotificationRepository;
import ra.edu.ss10.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }

    @Override
    public Transaction save(Long senderId, Long receiverId, Double amount, String note) {
        if (amount == null || amount <= 0) {
            throw new RuntimeException("Amount must be greater than 0");
        }

        Account sender = accountRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account receiver = accountRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        if (sender.getMoney() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        try {
            sender.setMoney(sender.getMoney() - amount);
            receiver.setMoney(receiver.getMoney() + amount);
            accountRepository.save(sender);
            accountRepository.save(receiver);

            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setMoney(amount);
            transaction.setNote(note);
            Transaction savedTransaction = transactionRepository.save(transaction);

            // Tạo thông báo
            Notification senderNoti = new Notification();
            senderNoti.setAccount(sender);
            senderNoti.setContent("Bạn đã chuyển " + amount + " VNĐ cho " + receiver.getFullName());

            Notification receiverNoti = new Notification();
            receiverNoti.setAccount(receiver);
            receiverNoti.setContent("Bạn đã nhận " + amount + " VNĐ từ " + sender.getFullName());

            notificationRepository.save(senderNoti);
            notificationRepository.save(receiverNoti);

            return savedTransaction;

        } catch (Exception e) {
            System.err.println("Lỗi giao dịch: " + e.getMessage());
            throw new RuntimeException("Transaction failed: " + e.getMessage());
        }
    }
}
