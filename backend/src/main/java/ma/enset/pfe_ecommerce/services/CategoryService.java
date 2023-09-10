package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategoryResponse;
import ma.enset.pfe_ecommerce.dtos.CategoryRequest;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.model.Category;


import java.util.List;

public interface CategoryService {
    CategoryResponse findCategory(Long idCategory) throws CategoryNotFoundException;
    Category findCategoryById(Long idCategory) throws CategoryNotFoundException;
    List<CategoryResponse> findAllCategories();
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(CategoryRequest categoryRequest);
    void deleteCategory(Long idCategory);
}
