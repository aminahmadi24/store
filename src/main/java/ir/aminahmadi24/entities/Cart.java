package ir.aminahmadi24.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.REMOVE}, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

    public Cart(Date createdAt) {
        this.createdAt = createdAt;
    }
}
