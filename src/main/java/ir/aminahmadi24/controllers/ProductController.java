package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.AddProductRequest;
import ir.aminahmadi24.dtos.ProductDto;
import ir.aminahmadi24.dtos.ErrorResponse;
import ir.aminahmadi24.entities.Product;
import ir.aminahmadi24.exceptions.CategoryNotFoundException;
import ir.aminahmadi24.mappers.ProductMapper;
import ir.aminahmadi24.services.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody AddProductRequest request){
        Product product = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductDto(product));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }
}
