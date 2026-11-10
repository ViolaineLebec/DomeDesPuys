package fr.simplon.domedespuys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String line;

    @Column(nullable=true, length=255)
    private String extraLine;

    @Column(nullable=false, length=50)
    private String city;

    @Column(nullable=false, length=6)
    private String zipCode;

    @Column(nullable=false, length=20)
    private String country;

    @Column(nullable=true, length=20)
    private String phone;

    @Column(nullable=false, length=50)
    private String firstname;

    @Column(nullable=false, length=50)
    private String lastname;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;
}
