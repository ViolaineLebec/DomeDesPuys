package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.OrderPostDTO;
import fr.simplon.domedespuys.dto.OrderPutDTO;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.services.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {
    @Override
    public List<Order> getCollection() {
        return List.of();
    }

    @Override
    public Order create(OrderPostDTO dto) {
        return null;
    }

    @Override
    public Order getById(String id) {
        return null;
    }

    @Override
    public Order update(String id, OrderPutDTO dto) {
        return null;
    }

}
