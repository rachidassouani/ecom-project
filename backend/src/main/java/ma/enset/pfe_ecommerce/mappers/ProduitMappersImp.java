package ma.enset.pfe_ecommerce.mappers;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Produit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProduitMappersImp {
    public ProduitDTO fromProduit(Produit produit){
        ProduitDTO produitDTO=new ProduitDTO();
        BeanUtils.copyProperties(produit,produitDTO);
        return produitDTO;
    }

    public Produit fromProduitDTO(ProduitDTO produitDTO){
        Produit produit=new Produit();
        BeanUtils.copyProperties(produitDTO,produit);
        return produit;
    }
}
