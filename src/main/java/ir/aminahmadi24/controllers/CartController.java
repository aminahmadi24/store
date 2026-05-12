package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.CreateCartResponse;
import ir.aminahmadi24.entities.Cart;
import ir.aminahmadi24.mappers.CartMapper;
import ir.aminahmadi24.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@AllArgsConstructor
@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @PostMapping
    public CreateCartResponse createCart(){
        Cart savedCart = cartRepository.save(new Cart(new Date()));
        return cartMapper.toCreateCartResponse(savedCart);
    }
}
