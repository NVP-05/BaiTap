package ra.edu.ss15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss15.model.dto.entity.Order;
import ra.edu.ss15.model.dto.request.OrderRequest;
import ra.edu.ss15.model.dto.request.UpdateOrderStatusRequest;
import ra.edu.ss15.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest req) {
        return new ResponseEntity<>(orderService.createOrder(req), HttpStatus.CREATED);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<List<Order>> myOrders() {
        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Order>> allOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Order> updateStatus(@PathVariable Long id, @RequestBody UpdateOrderStatusRequest req) {
        return ResponseEntity.ok(orderService.updateOrderStatus(id, req.getStatus()));
    }
}

