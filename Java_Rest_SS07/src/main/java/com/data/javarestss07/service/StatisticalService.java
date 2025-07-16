package com.data.javarestss07.service;

import java.util.Map;

public interface StatisticalService {
    int countRemainingSeeds();
    double totalHarvestMoneyThisMonth();
    double totalPaymentSlipsThisMonth();
    Map<String, Double> profitLossOverYear();
    double totalWorkerSalary();
}
