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
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=80)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, length=50)
    private String firstname;

    @Column(nullable=false, length=50)
    private String lastname;

    @Column(nullable=true, length=20)
    private String phone;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    List<Order> purchases;

    @OneToMany(mappedBy = "user")
    List<Address> addresses;
}
