package ra.edu.ss15.model.dto.request;

import java.util.List;

public class OrderRequest {
    private List<OrderItemRequest> items;
    public class OrderItemRequest {
        private Long productId;
        private int quantity;
    }
}
