package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.CategorieDTO;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Categorie;
import ma.enset.pfe_ecommerce.entites.Produit;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CategorieMappersImpl {
    public CategorieDTO fromCategorie(Categorie categorie){
        CategorieDTO categorieDTO=new CategorieDTO();
        BeanUtils.copyProperties(categorie,categorieDTO);
        return categorieDTO;
    }

    public Categorie fromCategorieDTO(CategorieDTO categorieDTO){
        Categorie categorie=new Categorie();
        BeanUtils.copyProperties(categorieDTO,categorie);
        return categorie;
    }
}
