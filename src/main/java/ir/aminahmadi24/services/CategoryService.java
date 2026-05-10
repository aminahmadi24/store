package ir.aminahmadi24.services;

import ir.aminahmadi24.dtos.CreateCategoryRequest;
import ir.aminahmadi24.entities.Category;
import ir.aminahmadi24.exceptions.DuplicatedCategoryException;
import ir.aminahmadi24.mappers.CategoryMapper;
import ir.aminahmadi24.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Category createCategory(CreateCategoryRequest request){
        if (categoryRepository.existsByName(request.getName()))
            throw new DuplicatedCategoryException();

        return categoryRepository.save(categoryMapper.toCategory(request));
    }
}
