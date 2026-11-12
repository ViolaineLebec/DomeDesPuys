package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.views.AddressView;
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
    @JsonView(AddressView.Id.class)
    private Long id;

    @Column(nullable=false)
    @JsonView(AddressView.Line.class)
    private String line;

    @Column(nullable=true, length=255)
    @JsonView(AddressView.Extraline.class)
    private String extraLine;

    @Column(nullable=false, length=50)
    @JsonView(AddressView.City.class)
    private String city;

    @Column(nullable=false, length=6)
    @JsonView(AddressView.Zipcode.class)
    private String zipCode;

    @Column(nullable=false, length=20)
    @JsonView(AddressView.Country.class)
    private String country;

    @Column(nullable=true, length=20)
    @JsonView(AddressView.Phone.class)
    private String phone;

    @Column(nullable=false, length=50)
    @JsonView(AddressView.Firstname.class)
    private String firstname;

    @Column(nullable=false, length=50)
    @JsonView(AddressView.Lastname.class)
    private String lastname;

    @Column(nullable=false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;
}
