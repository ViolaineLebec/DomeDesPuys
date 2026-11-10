package fr.simplon.domedespuys.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.entities.Brand;
import fr.simplon.domedespuys.repositories.BrandRepository;
import fr.simplon.domedespuys.views.BrandView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandRestController {

    private BrandRepository brandRepository;

    @GetMapping
    @JsonView(BrandView.BrandListView.class)
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/slug/{slug}")
    public List<Brand> getBrandsBySlug(@PathVariable String slug) {
        return brandRepository.findBrandBySlug(slug);
    }

    @GetMapping("/{id}")
    public Optional<Brand> getBrand(@PathVariable String id) {
        return brandRepository.findById(Long.valueOf(id));
    }
}