package ir.aminahmadi24.repositories;

import ir.aminahmadi24.entities.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    @EntityGraph(attributePaths = {"items"})
    @Query("SELECT c from Cart c WHERE c.id = :cartId")
    Optional<Cart> getCartWithItems(@Param(value = "cartId") UUID cartId);
}
