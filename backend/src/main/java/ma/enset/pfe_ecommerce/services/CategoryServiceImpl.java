package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategoryRequest;
import ma.enset.pfe_ecommerce.dtos.CategoryResponse;
import ma.enset.pfe_ecommerce.model.Category;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.mappers.CategoryMapperImpl;
import ma.enset.pfe_ecommerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryMapperImpl categoryMapperImpl;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapperImpl categoryMapperImpl) {
        this.categoryRepository = categoryRepository;
        this.categoryMapperImpl = categoryMapperImpl;
    }

    @Override
    public CategoryResponse findCategory(Long idCategory) throws CategoryNotFoundException {
        Category Category = categoryRepository.findById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return categoryMapperImpl.fromCategory(Category);
    }

    @Override
    public Category findCategoryById(Long idCategory) throws CategoryNotFoundException {
        return categoryRepository.findById(idCategory)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
    }

    @Override
    public List<CategoryResponse> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponse> categoryDTOS = categories.stream()
                .map(category -> categoryMapperImpl.fromCategory(category))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapperImpl.fromCategoryRequest(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapperImpl.fromCategory(savedCategory);
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapperImpl.fromCategoryRequest(categoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapperImpl.fromCategory(savedCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
