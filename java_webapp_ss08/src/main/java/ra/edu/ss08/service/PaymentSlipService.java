package ra.edu.ss08.service;

import ra.edu.ss08.model.entity.PaymentSlip;

import java.util.List;

public interface PaymentSlipService {
    PaymentSlip create(PaymentSlip paymentSlip);
    PaymentSlip update(Long id, PaymentSlip paymentSlip);
    void delete(Long id);
    List<PaymentSlip> findAll();
}
