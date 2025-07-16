package com.data.javarestss07.service;

import com.data.javarestss07.model.entity.Harvest;
import com.data.javarestss07.model.entity.PaymentSlip;
import com.data.javarestss07.model.entity.Worker;
import com.data.javarestss07.repository.HarvestRepository;
import com.data.javarestss07.repository.PaymentSlipRepository;
import com.data.javarestss07.repository.SeedRepository;
import com.data.javarestss07.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticalServiceImp implements StatisticalService {
    @Autowired
    private HarvestRepository harvestRepository;
    @Autowired
    private SeedRepository seedRepository;
    @Autowired
    private PaymentSlipRepository paymentSlipRepository;
    @Autowired
    private WorkerRepository workerRepository;
    @Override
    public int countRemainingSeeds() {
        return seedRepository.findAll().size();
    }
    @Override
    public double totalHarvestMoneyThisMonth() {
        LocalDate now = LocalDate.now();
        YearMonth currentMonth = YearMonth.of(now.getYear(), now.getMonth());
        List<Harvest> list = harvestRepository.findAll();

        return list.stream()
                .filter(h -> YearMonth.from(h.getCreatedAt()).equals(currentMonth))
                .mapToDouble(Harvest::getTotalMoney)
                .sum();
    }
    @Override
    public double totalPaymentSlipsThisMonth() {
        LocalDateTime start = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = start.plusMonths(1);
        List<PaymentSlip> slips = paymentSlipRepository.findByCreatedAtBetween(start, end);

        return slips.stream()
                .mapToDouble(PaymentSlip::getMoney)
                .sum();
    }
    @Override
    public Map<String, Double> profitLossOverYear() {
        Map<String, Double> result = new HashMap<>();
        int currentYear = LocalDate.now().getYear();

        for (int month = 1; month <= 12; month++) {
            YearMonth yearMonth = YearMonth.of(currentYear, month);
            LocalDate start = yearMonth.atDay(1);
            LocalDate end = yearMonth.atEndOfMonth();

            double income = harvestRepository.findAll().stream()
                    .filter(h -> !h.getCreatedAt().isBefore(start) && !h.getCreatedAt().isAfter(end))
                    .mapToDouble(Harvest::getTotalMoney)
                    .sum();

            int finalMonth = month;
            double expense = paymentSlipRepository.findAll().stream()
                    .filter(p -> {
                        LocalDateTime dt = p.getCreatedAt();
                        return dt.toLocalDate().getYear() == currentYear && dt.toLocalDate().getMonthValue() == finalMonth;
                    })
                    .mapToDouble(PaymentSlip::getMoney)
                    .sum();

            result.put("Tháng " + month, income - expense);
        }

        return result;
    }
    @Override
    public double totalWorkerSalary() {
        return workerRepository.findAll().stream().mapToDouble(Worker::getSalary).sum();
    }
}
