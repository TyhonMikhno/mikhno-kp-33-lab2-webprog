package Lab2Application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    boolean existsByCustomerNameAndPizzaIdAndQuantityAndIdNot(String customerName, Long pizzaId, int quantity, Long id);
}