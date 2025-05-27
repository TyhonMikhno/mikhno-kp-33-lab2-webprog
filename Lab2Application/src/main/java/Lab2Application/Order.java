package Lab2Application;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizzeria_id", nullable = false)
    private Pizzeria pizzeria;

    public Order() {}

    public Order(String customerName, int quantity, Pizza pizza, Pizzeria pizzeria) {
        this.customerName = customerName;
        this.quantity = quantity;
        this.pizza = pizza;
        this.pizzeria = pizzeria;
    }

    public Long getId() { return id; }
    public void setId(Long id) {
    this.id = id;
}

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public Pizza getPizza() { return pizza; }
    public void setPizza(Pizza pizza) { this.pizza = pizza; }

    public Pizzeria getPizzeria() { return pizzeria; }
    public void setPizzeria(Pizzeria pizzeria) { this.pizzeria = pizzeria; }
}
