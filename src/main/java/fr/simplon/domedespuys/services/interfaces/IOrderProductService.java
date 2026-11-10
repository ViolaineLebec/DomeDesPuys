package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.entities.OrderProduct;

public interface IOrderProductService<Oder, OrderProductDTO> {
    OrderProduct create(OrderProductDTO dto);
}
