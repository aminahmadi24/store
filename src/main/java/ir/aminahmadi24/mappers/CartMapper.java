package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.CreateCartResponse;
import ir.aminahmadi24.entities.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CreateCartResponse toCreateCartResponse(Cart cart){
        return  new CreateCartResponse(cart.getId(), cart.getCreatedAt());
    }
}
