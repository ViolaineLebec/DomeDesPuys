package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.repositories.OrderRepository;
import fr.simplon.domedespuys.views.OrderView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderRestController {
    private OrderRepository orderRepository;

    @GetMapping
    @JsonView(OrderView.OrderListView.class)
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(OrderView.OrderDetailView.class)
    public Optional<Order> getOrder(@PathVariable String id) {
        return orderRepository.findById(Long.valueOf(id));
    }
}
