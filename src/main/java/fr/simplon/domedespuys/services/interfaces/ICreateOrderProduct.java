package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.entities.OrderProduct;

public interface ICreateOrderProduct<Oder, OrderProductDTO> {
    OrderProduct create(OrderProductDTO dto);
}
