package Lab2Application;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzeria")
public class Pizzeria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "pizzeria", cascade = CascadeType.ALL)
    private List<Pizza> pizzas;

    public Pizzeria() {}

    public Pizzeria(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<Pizza> getPizzas() { return pizzas; }
    public void setPizzas(List<Pizza> pizzas) { this.pizzas = pizzas; }
}
