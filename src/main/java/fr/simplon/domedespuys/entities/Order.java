package fr.simplon.domedespuys.entities;

import com.fasterxml.jackson.annotation.JsonView;
import fr.simplon.domedespuys.views.OrderView;
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
@Table(name = "purchase")
public class Order {

    @Id
    @JsonView(OrderView.Id.class)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(OrderView.Number.class)
    @Column(length = 20, unique = true, nullable = false)
    private String number;

    @JsonView(OrderView.CreatedAt.class)
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @JsonView(OrderView.Status.class)
    @Column(nullable = false)
    private String status = EnumPurchaseStatus.PENDING.getValue();

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Address address;

    @OneToMany(mappedBy = "order")
    List<OrderProduct> orderProductList;

    public void addOrderProduct(OrderProduct orderProduct) {
        orderProductList.add(orderProduct);
    }
}