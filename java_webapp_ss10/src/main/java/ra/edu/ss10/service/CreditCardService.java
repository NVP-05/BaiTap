package ra.edu.ss10.service;

import ra.edu.ss10.model.entity.CreditCard;

public interface CreditCardService {
    CreditCard create(CreditCard creditCard);
    CreditCard updateLimit(Long id, Double newLimit);
    CreditCard updateStatus(Long id, String status);
}
