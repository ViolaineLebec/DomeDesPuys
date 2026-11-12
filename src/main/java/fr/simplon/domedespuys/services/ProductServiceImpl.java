package fr.simplon.domedespuys.services;

import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.repositories.BrandRepository;
import fr.simplon.domedespuys.repositories.ProductRepository;
import fr.simplon.domedespuys.services.interfaces.IProductService;
import fr.simplon.domedespuys.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;
    private BrandRepository brandRepository;
    private Slugger slugger;




    @Override
    public List<Product> getCollection() {
        return productRepository.findAll();
    }

    @Override
    public Product create(ProductDTO dto) {
        Product product = new Product();

        dtoToObject(dto, product);

        Optional<Brand> oBrand = brandRepository.findById(1L);
        Brand brand = oBrand.get();
        product.setBrand(brand);

        product.setSlug(slugger.slugify(dto.label));

//        Optional<User> oUser = userRepository.findById(1L);
//        User user = oUser.get();
//        product.setUser(user);

        LocalDateTime date = LocalDateTime.now();
        product.setCreatedAt(date);

        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public Product getById(String id) {
        return productRepository
                .findById(Long.parseLong(id))
                .orElse(null);
    }

    @Override
    public Product update(String id, ProductDTO dto) {
        Product product = getById(id);
        if (product != null) {
            dtoToObject(dto, product);
        }
        productRepository.saveAndFlush(product);
        return product;
    }

    @Override
    public boolean delete(String id) {
        try {
            productRepository.deleteById(Long.parseLong(id));
            return true;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void dtoToObject(ProductDTO dto, Product product) {
        product.setLabel(dto.label);
        product.setDescription(dto.description);
        product.setPrice(dto.price);
    }

}
