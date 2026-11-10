package fr.simplon.domedespuys.dto;

import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.OrderProduct;

import java.util.List;

public class OrderPostDTO {
    List<Address> addresses;
    List<OrderProduct> orderProducts;
}
