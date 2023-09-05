package ma.enset.pfe_ecommerce.web;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.CategoryDTO;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> categories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("{id}")
    public CategoryDTO findCategory(@PathVariable Long id) throws CategoryNotFoundException {
        return categoryService.findCategory(id);
    }

    @PostMapping
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categorieDTO) {
        return categoryService.saveCategory(categorieDTO);
    }

    @PutMapping("{id}")
    public CategoryDTO updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categorieDTO) {
        categorieDTO.setId(id);
        return categoryService.updateCategory(categorieDTO);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
