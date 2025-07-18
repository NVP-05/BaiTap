package ra.edu.ss08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss08.model.dto.response.ApiResponse;
import ra.edu.ss08.model.entity.PaymentSlip;
import ra.edu.ss08.service.PaymentSlipService;

import java.util.List;

@RestController
@RequestMapping("/payment-slips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentSlip>>> getAll() {
        List<PaymentSlip> result = paymentSlipService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(result, true, "Lấy danh sách phiếu chi thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentSlip>> create(@RequestBody PaymentSlip paymentSlip) {
        PaymentSlip saved = paymentSlipService.create(paymentSlip);
        return ResponseEntity.ok(new ApiResponse<>(saved, true, "Thêm phiếu chi thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentSlip>> update(@PathVariable Long id,
                                                           @RequestBody PaymentSlip paymentSlip) {
        PaymentSlip updated = paymentSlipService.update(id, paymentSlip);
        return ResponseEntity.ok(new ApiResponse<>(updated, true, "Cập nhật phiếu chi thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        paymentSlipService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(null, true, "Xóa phiếu chi thành công"));
    }
}
