package fr.simplon.domedespuys.services.interfaces;

import fr.simplon.domedespuys.dto.OrderPostDTO;
import fr.simplon.domedespuys.dto.OrderPutDTO;
import fr.simplon.domedespuys.entities.Order;

public interface IOrderService extends
        ICreate<Order, OrderPostDTO>,
        IUpdate<Order, OrderPutDTO>,
        IGetItem<Order>,
        ICollection<Order>
{
}
