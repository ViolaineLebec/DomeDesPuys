package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.OrderProductDTO;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.entities.OrderProduct;

public interface IOrderProductService {
    OrderProduct create(OrderProductDTO dto, Order order);
}
