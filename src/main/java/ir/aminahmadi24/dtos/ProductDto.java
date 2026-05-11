package ir.aminahmadi24.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private Long price;
    private int stockQuantity;
}
