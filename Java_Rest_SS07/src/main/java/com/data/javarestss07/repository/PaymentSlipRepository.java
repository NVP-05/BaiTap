package com.data.javarestss07.repository;
import com.data.javarestss07.model.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
    List<PaymentSlip> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}