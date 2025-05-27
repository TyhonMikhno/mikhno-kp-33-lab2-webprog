package Lab2Application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final PizzaRepository pizzaRepository;
    private final PizzeriaRepository pizzeriaRepository;

    @Autowired
    public OrderController(OrderService orderService,
                           PizzaRepository pizzaRepository,
                           PizzeriaRepository pizzeriaRepository) {
        this.orderService = orderService;
        this.pizzaRepository = pizzaRepository;
        this.pizzeriaRepository = pizzeriaRepository;
    }

    // 🔄 Автоматично підставляти список піцерій у всі шаблони
    @ModelAttribute("pizzerias")
    public List<Pizzeria> loadPizzerias() {
        return pizzeriaRepository.findAll();
    }

    // 📋 Список замовлень
    @GetMapping
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "order-list";
    }

    // ➕ Форма створення
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "order-form";
    }

    @PostMapping
public String saveOrUpdateOrder(@ModelAttribute("order") Order order,
                                 BindingResult result,
                                 Model model) {
    Long pizzaId = order.getPizza().getId();
    int quantity = order.getQuantity();
    String customerName = order.getCustomerName();

    boolean isDuplicate = (order.getId() == null)
            ? orderService.orderExists(customerName, quantity, pizzaId)
            : orderService.orderExistsExceptCurrent(customerName, quantity, pizzaId, order.getId());

    if (isDuplicate) {
        result.rejectValue("customerName", "duplicate", "Таке замовлення вже існує.");
    }

    if (quantity < 1) {
        result.rejectValue("quantity", "invalid", "Кількість має бути принаймні 1.");
    }

    if (result.hasErrors()) {
        model.addAttribute("pizzas", pizzaRepository.findAll());
        model.addAttribute("pizzerias", pizzeriaRepository.findAll());
        return "order-form";
    }

    // 🔧 Головна частина: якщо ID є — редагування
    if (order.getId() != null) {
        Optional<Order> existingOpt = orderService.getOrderById(order.getId());
        if (existingOpt.isPresent()) {
            Order existing = existingOpt.get();
            existing.setCustomerName(order.getCustomerName());
            existing.setQuantity(order.getQuantity());
            existing.setPizza(order.getPizza());
            existing.setPizzeria(order.getPizzeria());
            orderService.saveOrder(existing);
            return "redirect:/orders";
        }
    }

    // Якщо ID нема — створення нового
    orderService.saveOrder(order);
    return "redirect:/orders";
}

    // ✏️ Редагування
    @GetMapping("/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Order> orderOpt = orderService.getOrderById(id);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", orderOpt.get());
            model.addAttribute("pizzas", pizzaRepository.findAll());
            return "order-form";
        } else {
            return "redirect:/orders";
        }
    }

    // 🗑️ Видалення
    @GetMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}
