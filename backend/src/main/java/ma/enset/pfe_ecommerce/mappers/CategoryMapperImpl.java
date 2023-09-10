package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.CategoryRequest;
import ma.enset.pfe_ecommerce.dtos.CategoryResponse;
import ma.enset.pfe_ecommerce.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperImpl {

    public CategoryResponse fromCategory(Category category){
        CategoryResponse categoryDTO = new CategoryResponse();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    public Category fromCategoryRequest(CategoryRequest categoryRequest){
        Category category = new Category();
        BeanUtils.copyProperties(categoryRequest, category);
        return category;
    }
}
