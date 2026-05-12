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

    @OneToMany(mappedBy = "cart", cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE}, orphanRemoval = true)
    private Set<CartItem> items = new HashSet<>();

    public Cart(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CartItem getItem(Long productId) {
        return items
                .stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst()
                .orElse(null);
    }

    public Long getTotalPrice(){
        Long totalPrice = 0L;
        for (CartItem item: items){
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }



    public CartItem addItem(Product product) {
        CartItem item = getItem(product.getId());
        if (item == null) {
            item = new CartItem();
            item.setCart(this);
            item.setQuantity(1);
            item.setProduct(product);
            items.add(item);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
        return item;
    }
}
