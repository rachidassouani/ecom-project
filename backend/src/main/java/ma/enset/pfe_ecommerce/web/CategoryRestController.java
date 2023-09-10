package ma.enset.pfe_ecommerce.web;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.CategoryResponse;
import ma.enset.pfe_ecommerce.dtos.CategoryRequest;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/categories")
@CrossOrigin("*")
public class CategoryRestController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> categories(){
        return categoryService.findAllCategories();
    }

    @GetMapping("{id}")
    public CategoryResponse findCategory(@PathVariable Long id) throws CategoryNotFoundException {
        return categoryService.findCategory(id);
    }

    @PostMapping
    public CategoryResponse saveCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }

    @PutMapping("{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        categoryRequest.setId(id);
        return categoryService.updateCategory(categoryRequest);
    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
