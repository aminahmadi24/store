package ir.aminahmadi24.dtos;

import lombok.Data;

@Data
public class CartItemDto {
    private CartProductDto product;
    private Integer quantity;
    private Long totalPrice;
}
