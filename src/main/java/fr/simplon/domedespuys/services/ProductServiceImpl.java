package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repositories.ProductRepository;
import fr.simplon.domedespuys.services.interfaces.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;


    @Override
    public List<Product> getCollection() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductDTO dto) {
        return null;
    }

    @Override
    public Product getById(String id) {
        return productRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Product update(String id, ProductDTO dto) {
        return null;
    }

}
