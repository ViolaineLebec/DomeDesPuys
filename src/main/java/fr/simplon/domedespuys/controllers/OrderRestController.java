package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.services.OrderServiceImpl;
import fr.simplon.domedespuys.views.OrderView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/order")
public class OrderRestController {

    private OrderServiceImpl orderService;

    @GetMapping
    @JsonView(OrderView.OrderListView.class)
    public List<Order> getOrders() {
        return orderService.getCollection();
    }

    @GetMapping("/{id}")
    @JsonView(OrderView.OrderDetailView.class)
    public Order getOrder(@PathVariable String id) {
        return orderService.getById(id);
    }
}
