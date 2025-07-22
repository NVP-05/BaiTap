package ra.edu.ss10.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss10.model.entity.Account;
import ra.edu.ss10.model.entity.CreditCard;
import ra.edu.ss10.repository.AccountRepository;
import ra.edu.ss10.repository.CreditCardRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreditCardServiceImp implements CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public CreditCard create(CreditCard creditCard) {
        try {
            if (creditCard.getAccount() == null || creditCard.getAccount().getId() == null) {
                throw new IllegalArgumentException("Account is required");
            }

            Optional<Account> optionalAccount = accountRepository.findById(creditCard.getAccount().getId());
            if (optionalAccount.isEmpty()) {
                throw new RuntimeException("Account not found");
            }

            boolean exists = creditCardRepository.existsByAccountId(creditCard.getAccount().getId());
            if (exists) {
                throw new RuntimeException("Account already has a credit card");
            }

            if (creditCard.getSpendingLimit() == null || creditCard.getSpendingLimit() < 0) {
                throw new IllegalArgumentException("Spending limit must be non-negative");
            }

            creditCard.setAmountSpent(0.0);
            creditCard.setStatus("active");

            return creditCardRepository.save(creditCard);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create credit card: " + e.getMessage(), e);
        }
    }

    @Override
    public CreditCard updateLimit(Long id, Double newLimit) {
        try {
            if (newLimit == null || newLimit < 0) {
                throw new IllegalArgumentException("Spending limit must be non-negative");
            }

            CreditCard card = creditCardRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Credit card not found"));

            card.setSpendingLimit(newLimit);
            return creditCardRepository.save(card);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update spending limit: " + e.getMessage(), e);
        }
    }

    @Override
    public CreditCard updateStatus(Long id, String status) {
        try {
            if (!"active".equalsIgnoreCase(status) && !"inactive".equalsIgnoreCase(status)) {
                throw new IllegalArgumentException("Status must be 'active' or 'inactive'");
            }

            CreditCard card = creditCardRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Credit card not found"));

            card.setStatus(status.toLowerCase());
            return creditCardRepository.save(card);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update status: " + e.getMessage(), e);
        }
    }
}
