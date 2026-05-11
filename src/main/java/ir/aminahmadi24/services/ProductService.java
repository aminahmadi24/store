package ir.aminahmadi24.services;

import ir.aminahmadi24.dtos.AddProductRequest;
import ir.aminahmadi24.entities.Category;
import ir.aminahmadi24.entities.Product;
import ir.aminahmadi24.exceptions.CategoryNotFoundException;
import ir.aminahmadi24.repositories.CategoryRepository;
import ir.aminahmadi24.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public Product addProduct(AddProductRequest request){
        Category category = categoryRepository.findById((long) request.getCategoryId())
                .orElseThrow(CategoryNotFoundException::new);

        Product product = productRepository.findByName(request.getName().toLowerCase()).orElse(null);
        if(product != null){
            product.setStockQuantity(product.getStockQuantity() + request.getStockQuantity());
        }else{
            product = new Product(request.getName().toLowerCase(), request.getPrice(), request.getStockQuantity());
            product.assignCategoryToProduct(category);
        }
        return productRepository.save(product);
    }
}
