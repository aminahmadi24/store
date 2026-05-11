package ir.aminahmadi24.dtos;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductStockQuantityRequest {
    @Min(value = 1)
    private int stockQuantity;
}
