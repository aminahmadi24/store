package ir.aminahmadi24.mappers;

import ir.aminahmadi24.dtos.CreateCategoryRequest;
import ir.aminahmadi24.entities.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {

    public Category toCategory(CreateCategoryRequest request){
        return new Category(request.getName().toLowerCase());
    }
}
