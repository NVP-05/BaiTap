package com.data.javarestss07.controller;

import com.data.javarestss07.model.entity.PaymentSlip;
import com.data.javarestss07.model.response.DataResponse;
import com.data.javarestss07.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("paymentslips")
public class PaymentSlipController {
    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<DataResponse<List<PaymentSlip>>> getPaymentSlips() {
        return new ResponseEntity<>(new DataResponse<>(paymentSlipService.getAllPaymentSlips(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<PaymentSlip>> getPaymentSlipById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(paymentSlipService.getPaymentSlipById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<PaymentSlip>> addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        return new ResponseEntity<>(new DataResponse<>(paymentSlipService.addPaymentSlip(paymentSlip), HttpStatus.CREATED), HttpStatus.CREATED);
    }
}