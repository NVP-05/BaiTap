package ra.edu.ss08.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss08.model.entity.PaymentSlip;
import ra.edu.ss08.repository.PaymentSlipRepository;

import java.util.List;

@Service
public class PaymentSlipServiceImp implements PaymentSlipService{
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;
    @Override
    public PaymentSlip create(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }

    @Override
    public PaymentSlip update(Long id, PaymentSlip paymentSlip) {
        PaymentSlip pay = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("khong ton tai"));
        pay.setId(id);
        return paymentSlipRepository.save(pay);
    }

    @Override
    public void delete(Long id) {
        PaymentSlip pay = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("khong ton tai"));
        paymentSlipRepository.delete(pay);
    }

    @Override
    public List<PaymentSlip> findAll() {
        return paymentSlipRepository.findAll();
    }
}
