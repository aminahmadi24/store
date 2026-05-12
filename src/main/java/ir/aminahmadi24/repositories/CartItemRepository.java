package ir.aminahmadi24.repositories;

import ir.aminahmadi24.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
