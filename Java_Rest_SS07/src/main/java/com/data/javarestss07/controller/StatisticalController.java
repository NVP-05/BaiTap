package com.data.javarestss07.controller;

import com.data.javarestss07.model.response.DataResponse;
import com.data.javarestss07.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistical")
public class StatisticalController {
    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public ResponseEntity<DataResponse<Integer>> getRemainingSeeds() {
        return new ResponseEntity<>(new DataResponse<>(statisticalService.countRemainingSeeds(), HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("/harvest-money")
    public ResponseEntity<DataResponse<Double>> getHarvestMoney() {
        return new ResponseEntity<>(new DataResponse<>(statisticalService.totalHarvestMoneyThisMonth(), HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("/payment-slips")
    public ResponseEntity<DataResponse<Double>> getPaymentSlips() {
        return new ResponseEntity<>(new DataResponse<>(statisticalService.totalPaymentSlipsThisMonth(), HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("/profit-loss")
    public ResponseEntity<DataResponse<Map<String, Double>>> getProfitLoss() {
        return new ResponseEntity<>(new DataResponse<>(statisticalService.profitLossOverYear(), HttpStatus.OK), HttpStatus.OK);
    }
    @GetMapping("//worker-salary")
    public ResponseEntity<DataResponse<Double>> getWorkerSalary() {
        return new ResponseEntity<>(new DataResponse<>(statisticalService.totalWorkerSalary(), HttpStatus.OK), HttpStatus.OK);
    }
}
