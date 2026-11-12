package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.views.ProductView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonView(ProductView.Id.class)
    private Long id;

    @Column(nullable=false, length=50)
    @JsonView(ProductView.Label.class)
    private String label;

    @Column(nullable=true, columnDefinition = "TEXT")
    @JsonView(ProductView.Description.class)
    private String description;

    @Column(nullable=false)
    @JsonView(ProductView.Price.class)
    private Long price;

    @Column(nullable=false, length=50)
    @JsonView(ProductView.Slug.class)
    private String slug;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    @JsonView(ProductView.Brand.class)
    private Brand brand;

    @OneToMany(mappedBy = "product")
    @JsonView(ProductView.Images.class)
    List<Image> images;
}
