package ir.aminahmadi24.dtos;

import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
public class AddProductToCartRequest {
    @Min(value = 1)
    private Long productId;

}
