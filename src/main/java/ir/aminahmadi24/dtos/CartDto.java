package ir.aminahmadi24.dtos;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class CartDto {
    private UUID id;
    private List<CartItemDto> items;
    private Long totalPrice;
}
