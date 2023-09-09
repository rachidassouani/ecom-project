package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategoryDTO;
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
    public CategoryDTO findCategory(Long idCategory) throws CategoryNotFoundException {
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
    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> categoryMapperImpl.fromCategory(category))
                .collect(Collectors.toList());
        return categoryDTOS;
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapperImpl.fromCategoryDTO(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapperImpl.fromCategory(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        System.out.println("good1");
        Category category = categoryMapperImpl.fromCategoryDTO(categoryDTO);
        System.out.println("good2");
        Category savedCategory = categoryRepository.save(category);
        System.out.println("good3");
        return categoryMapperImpl.fromCategory(savedCategory);
    }

    @Override
    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
