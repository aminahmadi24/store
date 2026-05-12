package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.CartItemDto;
import ir.aminahmadi24.dtos.CartProductDto;
import ir.aminahmadi24.dtos.CreateCartResponse;
import ir.aminahmadi24.entities.Cart;
import ir.aminahmadi24.entities.CartItem;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public CreateCartResponse toCreateCartResponse(Cart cart){
        return  new CreateCartResponse(cart.getId(), cart.getCreatedAt());
    }

    public CartProductDto toCartProductDto(CartItem item){
        CartProductDto cartProductDto = new CartProductDto();
        cartProductDto.setId(item.getProduct().getId());
        cartProductDto.setQuantity(item.getQuantity());
        cartProductDto.setPrice(item.getProduct().getPrice());
        return cartProductDto;
    }

    public CartItemDto toCartItemDto(CartItem item){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProduct(toCartProductDto(item));
        cartItemDto.setQuantity(item.getQuantity());
        cartItemDto.setTotalPrice(item.getTotalPrice());
        return cartItemDto;
    }
}
