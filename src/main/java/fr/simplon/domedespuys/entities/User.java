package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.views.UserView;
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
    @JsonView(UserView.Id.class)
    private Long id;

    @Column(nullable=false, length=80)
    @JsonView(UserView.Email.class)
    private String email;

    @Column(nullable=false)
    @JsonView(UserView.Password.class)
    private String password;

    @Column(nullable=false, length=50)
    @JsonView(UserView.Firstname.class)
    private String firstname;

    @Column(nullable=false, length=50)
    @JsonView(UserView.Lastname.class)
    private String lastname;

    @Column(nullable=true, length=20)
    @JsonView(UserView.Phone.class)
    private String phone;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.Purchases.class)
    List<Order> purchases;

    @OneToMany(mappedBy = "user")
    @JsonView(UserView.Addresses.class)
    List<Address> addresses;
}
