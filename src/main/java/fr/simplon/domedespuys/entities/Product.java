package fr.simplon.domedespuys.entities;

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
    private Long id;

    @Column(nullable=false, length=50)
    private String label;

    @Column(nullable=true, columnDefinition = "TEXT")
    private String description;

    @Column(nullable=false)
    private Long price;

    @Column(nullable=false, length=50)
    private String slug;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product")
    List<Image> images;
}
