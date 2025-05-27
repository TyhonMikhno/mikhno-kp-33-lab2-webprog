package Lab2Application;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pizzeria_id", nullable = false)
    private Pizzeria pizzeria;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Pizza() {}

    public Pizza(String name, String size, double price, Pizzeria pizzeria) {
        this.name = name;
        this.size = size;
        this.price = price;
        this.pizzeria = pizzeria;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Pizzeria getPizzeria() { return pizzeria; }
    public void setPizzeria(Pizzeria pizzeria) { this.pizzeria = pizzeria; }

    public List<Order> getOrders() { return orders; }
    public void setOrders(List<Order> orders) { this.orders = orders; }
}
