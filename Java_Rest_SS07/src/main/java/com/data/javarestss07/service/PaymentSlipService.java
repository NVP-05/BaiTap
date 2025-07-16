package com.data.javarestss07.service;
import com.data.javarestss07.model.entity.PaymentSlip;
import com.data.javarestss07.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
@Service
public class PaymentSlipService {
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }
    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Khong ton tai!"));
    }
    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }
}