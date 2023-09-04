package ma.enset.pfe_ecommerce.web;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.CategorieDTO;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.exceptions.CategorieNotFoundException;
import ma.enset.pfe_ecommerce.services.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor
public class CategorieRestControlleur {
    private CategorieService categorieService;

    @GetMapping(path = "/categories")
    public List<CategorieDTO> categories(){
        return categorieService.listcategories();
    }

    @GetMapping(path = "/categories/{id}")
    public CategorieDTO getcategorie(@PathVariable Long id) throws CategorieNotFoundException {
        return categorieService.getcategorie(id);
    }

    @PostMapping(path = "/categories")
    public CategorieDTO savecategorie(@RequestBody CategorieDTO categorieDTO) {
        return categorieService.savecategorie(categorieDTO);
    }

    @PutMapping(path = "/categories/{id}")
    public CategorieDTO updatecategorie(@PathVariable Long id, @RequestBody CategorieDTO categorieDTO) {
        categorieDTO.setId(id);
        return categorieService.updatecategorie(categorieDTO);
    }

    @DeleteMapping(path = "/categories/{id}")
    public void deletecategorie(@PathVariable Long id){
        categorieService.deletecategorie(id);
    }

}
