package ra.edu.ss15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.ss15.model.dto.entity.Order;
import ra.edu.ss15.model.dto.entity.OrderItem;
import ra.edu.ss15.model.dto.entity.OrderStatus;
import ra.edu.ss15.model.dto.entity.User;
import ra.edu.ss15.model.dto.request.OrderRequest;
import ra.edu.ss15.repository.OrderItemRepository;
import ra.edu.ss15.repository.OrderRepository;
import ra.edu.ss15.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;
    @Autowired private OrderItemRepository itemRepo;
    @Autowired private ProductRepository productRepo;
    @Autowired private UserService userService;

    @Override
    public Order createOrder(OrderRequest req) {
        User currentUser = userService.getByUsername();

        Order order = new Order();
        order.setUser(currentUser);
        order.setCreatedDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest i : req.getItems()) {
            Product product = productRepo.findById(i.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(i.getQuantity());
            item.setPriceBuy(product.getPrice());
            orderItems.add(item);

            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(i.getQuantity())));
        }

        order.setTotalMoney(total);
        order.setItems(orderItems);
        orderRepo.save(order);
        return order;
    }

    @Override
    public List<Order> getMyOrders() {
        User user = userService.getCurrentUser();
        return orderRepo.findByUser(user);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order updateOrderStatus(Long id, OrderStatus status) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepo.save(order);
    }
}

