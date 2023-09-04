package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategorieDTO;
import ma.enset.pfe_ecommerce.exceptions.CategorieNotFoundException;


import java.util.List;

public interface CategorieService {
    CategorieDTO getcategorie(Long idcat) throws CategorieNotFoundException;

    public List<CategorieDTO> listcategories();

    CategorieDTO savecategorie(CategorieDTO categorieDTO);

    CategorieDTO updatecategorie(CategorieDTO categorieDTO);

    void deletecategorie(Long idcat);

}
