package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategoryDTO;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.model.Category;


import java.util.List;

public interface CategoryService {
    CategoryDTO findCategory(Long idCategory) throws CategoryNotFoundException;
    Category findCategoryById(Long idCategory) throws CategoryNotFoundException;
    List<CategoryDTO> findAllCategories();
    CategoryDTO saveCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(CategoryDTO categoryDTO);
    void deleteCategory(Long idCategory);
}
