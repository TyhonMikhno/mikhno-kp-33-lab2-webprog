package Lab2Application;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Optional<Order> getOrderById(Long id);
    Order saveOrder(Order order);
    void deleteOrder(Long id);
    boolean orderExists(String customerName, int quantity, Long pizzaId);
    boolean orderExistsExceptCurrent(String customerName, int quantity, Long pizzaId, Long currentId);
}
