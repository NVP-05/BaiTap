package ra.edu.ss15.service;

import ra.edu.ss15.model.dto.entity.Order;
import ra.edu.ss15.model.dto.entity.OrderStatus;
import ra.edu.ss15.model.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest);
    List<Order> getMyOrders();
    List<Order> getAllOrders();
    Order updateOrderStatus(Long id, OrderStatus status);
}
