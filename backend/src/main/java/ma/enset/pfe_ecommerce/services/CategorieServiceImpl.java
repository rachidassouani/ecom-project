package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategorieDTO;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Categorie;
import ma.enset.pfe_ecommerce.entites.Produit;
import ma.enset.pfe_ecommerce.exceptions.CategorieNotFoundException;
import ma.enset.pfe_ecommerce.exceptions.ProduitNotFoundException;
import ma.enset.pfe_ecommerce.mappers.CategorieMappersImpl;
import ma.enset.pfe_ecommerce.repositories.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService{
    private CategorieRepository categorieRepository;
    private CategorieMappersImpl categorieMappers;

    public CategorieServiceImpl(CategorieRepository categorieRepository, CategorieMappersImpl categorieMappers) {
        this.categorieRepository = categorieRepository;
        this.categorieMappers = categorieMappers;
    }

    @Override
    public CategorieDTO getcategorie(Long idcat) throws CategorieNotFoundException {
        Categorie categorie = categorieRepository.findById(idcat)
                .orElseThrow(() -> new CategorieNotFoundException("categorie not found"));
        return categorieMappers.fromCategorie(categorie);
    }

    @Override
    public List<CategorieDTO> listcategories() {
        List<Categorie> categories = categorieRepository.findAll();
        List<CategorieDTO> categorieDTOS = categories.stream()
                .map(categorie -> categorieMappers.fromCategorie(categorie))
                .collect(Collectors.toList());
        return categorieDTOS;
    }

    @Override
    public CategorieDTO savecategorie(CategorieDTO categorieDTO) {
        Categorie categorie= categorieMappers.fromCategorieDTO(categorieDTO);
        Categorie saveCategorie = categorieRepository.save(categorie);
        return categorieMappers.fromCategorie(saveCategorie);
    }

    @Override
    public CategorieDTO updatecategorie(CategorieDTO categorieDTO) {
        Categorie categorie= categorieMappers.fromCategorieDTO(categorieDTO);
        Categorie saveCategorie = categorieRepository.save(categorie);
        return categorieMappers.fromCategorie(saveCategorie);
    }

    @Override
    public void deletecategorie(Long idcat) {
        categorieRepository.deleteById(idcat);
    }
}
