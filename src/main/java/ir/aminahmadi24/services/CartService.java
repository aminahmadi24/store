package ir.aminahmadi24.services;

import ir.aminahmadi24.dtos.AddProductToCartRequest;
import ir.aminahmadi24.dtos.CartDto;
import ir.aminahmadi24.dtos.CartItemDto;
import ir.aminahmadi24.entities.Cart;
import ir.aminahmadi24.entities.CartItem;
import ir.aminahmadi24.entities.Product;
import ir.aminahmadi24.exceptions.CartNotFoundException;
import ir.aminahmadi24.exceptions.ProductNotFoundException;
import ir.aminahmadi24.mappers.CartMapper;
import ir.aminahmadi24.repositories.CartRepository;
import ir.aminahmadi24.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;


    public CartItemDto addItemToCart(UUID cartId, AddProductToCartRequest request){
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(ProductNotFoundException::new);
        CartItem cartItem = cart.addItem(product);
        cartRepository.save(cart);
        return cartMapper.toCartItemDto(cartItem);
    }

    public CartDto getCart(UUID cartId){
        Cart cart = cartRepository.getCartWithItems(cartId).orElseThrow(CartNotFoundException::new);
        return  cartMapper.toCartDto(cart);
    }

}
