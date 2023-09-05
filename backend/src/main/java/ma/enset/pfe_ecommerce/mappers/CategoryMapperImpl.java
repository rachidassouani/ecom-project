package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.CategoryDTO;
import ma.enset.pfe_ecommerce.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperImpl {

    public CategoryDTO fromCategory(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        BeanUtils.copyProperties(category, categoryDTO);
        return categoryDTO;
    }

    public Category fromCategoryDTO(CategoryDTO categorieDTO){
        Category category = new Category();
        BeanUtils.copyProperties(categorieDTO, category);
        return category;
    }
}
