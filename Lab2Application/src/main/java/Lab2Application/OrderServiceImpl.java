package Lab2Application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public boolean orderExists(String customerName, int quantity, Long pizzaId) {
        return orderRepository.existsByCustomerNameAndPizzaIdAndQuantityAndIdNot(
                customerName, pizzaId, quantity, -1L);
    }

    @Override
    public boolean orderExistsExceptCurrent(String customerName, int quantity, Long pizzaId, Long currentId) {
        return orderRepository.existsByCustomerNameAndPizzaIdAndQuantityAndIdNot(
                customerName, pizzaId, quantity, currentId);
    }
}
