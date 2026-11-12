package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.dto.AddressDTO;
import fr.simplon.domedespuys.dto.ProductDTO;
import fr.simplon.domedespuys.entities.Address;
import fr.simplon.domedespuys.entities.Product;
import fr.simplon.domedespuys.services.ProductServiceImpl;
import fr.simplon.domedespuys.views.AddressView;
import fr.simplon.domedespuys.views.ProductView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private ProductServiceImpl productService;

    @GetMapping
    @JsonView(ProductView.ProductListView.class)
    public List<Product> getProducts() {
        return productService.getCollection();
    }

//    @GetMapping("/slug/{slug}")
//    @JsonView(ProductView.ProductDetailView.class)
//    public List<Product> getProductsBySlug(@PathVariable String slug) {
//        return productRepository.findProductBySlug(slug);
//    }

    @GetMapping("/{id}")
    @JsonView(ProductView.ProductDetailView.class)
    public Product getProduct(@PathVariable String id) {
        return productService.getById(id);
    }

    @PostMapping
    @JsonView(ProductView.ProductDetailView.class)
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PutMapping("/{id}")
    @JsonView(ProductView.ProductDetailView.class)
    public Product updateProduct(@PathVariable String id, @RequestBody ProductDTO dto) {
        return productService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @JsonView(ProductView.ProductDetailView.class)
    public boolean delete(@PathVariable String id) {
        return productService.delete(id);
    }
}
