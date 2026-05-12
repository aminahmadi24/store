package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.CartDto;
import ir.aminahmadi24.dtos.CartItemDto;
import ir.aminahmadi24.dtos.CartProductDto;
import ir.aminahmadi24.dtos.CreateCartResponse;
import ir.aminahmadi24.entities.Cart;
import ir.aminahmadi24.entities.CartItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public List<CartItemDto> convertCartItemSetToCartItemDtoList(Set<CartItem> items){
        return items
                .stream()
                .map(this::toCartItemDto)
                .toList();
    }
    public CartDto toCartDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setItems(convertCartItemSetToCartItemDtoList(cart.getItems()));
        cartDto.setTotalPrice(cart.getTotalPrice());
        return cartDto;
    }
}
