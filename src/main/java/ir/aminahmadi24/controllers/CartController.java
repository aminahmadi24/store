package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.AddProductToCartRequest;
import ir.aminahmadi24.dtos.CartItemDto;
import ir.aminahmadi24.dtos.CreateCartResponse;
import ir.aminahmadi24.dtos.ErrorResponse;
import ir.aminahmadi24.entities.Cart;
import ir.aminahmadi24.exceptions.CartNotFoundException;
import ir.aminahmadi24.exceptions.ProductNotFoundException;
import ir.aminahmadi24.mappers.CartMapper;
import ir.aminahmadi24.repositories.CartRepository;
import ir.aminahmadi24.services.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartService cartService;

    @PostMapping
    public CreateCartResponse createCart(){
        Cart savedCart = cartRepository.save(new Cart(new Date()));
        return cartMapper.toCreateCartResponse(savedCart);
    }

    @PostMapping("/items/{cartId}")
    public CartItemDto addItemToCart(
            @PathVariable(name = "cartId") UUID cartId,
            @Valid @RequestBody AddProductToCartRequest request){
        return cartService.addItemToCart(cartId, request);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCartNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ex.getMessage()));
    }
}
