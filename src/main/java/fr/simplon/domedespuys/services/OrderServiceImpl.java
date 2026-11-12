package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.OrderPostDTO;
import fr.simplon.domedespuys.dto.OrderProductDTO;
import fr.simplon.domedespuys.dto.OrderPutDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.entities.OrderProduct;
import fr.simplon.domedespuys.repositories.AddressRepository;
import fr.simplon.domedespuys.repositories.OrderRepository;
import fr.simplon.domedespuys.services.interfaces.IOrderProductService;
import fr.simplon.domedespuys.services.interfaces.IOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private OrderRepository orderRepository;
    private AddressRepository addressRepository;
    private IOrderProductService iOrderProductService;


    @Override
    public List<Order> getCollection() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(OrderPostDTO dto) {
        Order order = new Order();

        for (OrderProductDTO orderProductDTO : dto.orderProducts) {
            OrderProduct op = iOrderProductService.create(orderProductDTO, order);
            if (op != null) {
                order.addOrderProduct(op);
            }
        }

        Optional<Address> addressOptional = addressRepository.findById(Long.parseLong(dto.addressId));
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            order.setAddress(address);
            order.setUser(address.getUser());
        }

        orderRepository.saveAndFlush(order);
        return order;
    }

    @Override
    public Order getById(String id) {
        return orderRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Order update(String id, OrderPutDTO dto) {
        Order order = getById(id);
        if (order != null) {
            order.setStatus(dto.status);
        }
        return order;
    }

}
