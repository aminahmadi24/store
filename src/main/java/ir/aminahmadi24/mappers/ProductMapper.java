package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.ProductDto;
import ir.aminahmadi24.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductDto toProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStockQuantity());
    }
}
