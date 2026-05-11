package ir.aminahmadi24.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AddProductRequest {

    @NotBlank
    private String name;

    @Min(value = 1)
    private Long price;

    @Min(value = 1)
    private int stockQuantity;

    @Min(value = 1)
    private int categoryId;
}
