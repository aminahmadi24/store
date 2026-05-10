package ir.aminahmadi24.controllers;

import ir.aminahmadi24.dtos.CreateCategoryRequest;
import ir.aminahmadi24.dtos.ErrorResponse;
import ir.aminahmadi24.entities.Category;
import ir.aminahmadi24.exceptions.DuplicatedCategoryException;
import ir.aminahmadi24.mappers.CategoryMapper;
import ir.aminahmadi24.repositories.CategoryRepository;
import ir.aminahmadi24.services.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CreateCategoryRequest request){
        Category category = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @ExceptionHandler(DuplicatedCategoryException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedCategory(Exception ex){
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }

}
