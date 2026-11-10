package fr.simplon.domedespuys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String label;

    @Column(nullable=true, columnDefinition = "TEXT")
    private String logo;

    @Column(nullable=false)
    private String slug;

    @OneToMany(mappedBy = "brand")
    List<Product> products;
}
