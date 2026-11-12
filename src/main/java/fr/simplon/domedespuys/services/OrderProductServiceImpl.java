package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.OrderProductDTO;
import fr.simplon.domedespuys.entities.Order;
import fr.simplon.domedespuys.entities.OrderProduct;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repositories.OrderProductRepository;
import fr.simplon.domedespuys.repositories.ProductRepository;
import fr.simplon.domedespuys.services.interfaces.IOrderProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderProductServiceImpl implements IOrderProductService {

    private ProductRepository productRepository;
    private OrderProductRepository orderProductRepository;

    @Override
    public OrderProduct create(OrderProductDTO dto, Order order) {
        Optional<Product> oProduct = productRepository.findById(Long.parseLong(dto.idProduct));
        if(oProduct.isPresent()) {
            Product product = oProduct.get();

            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setQuantity(dto.quantity);
            orderProduct.setPrice(dto.price);
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            orderProductRepository.save(orderProduct);
            return orderProduct;
        } else {
            return null;
        }
    }
}
