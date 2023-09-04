package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.CategorieDTO;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.exceptions.ProduitNotFoundException;


import java.util.List;


public interface ProduitService {

   //les produits
   ProduitDTO getproduit(Long id) throws ProduitNotFoundException;

   public List<ProduitDTO> listprod();

   ProduitDTO saveproduit(ProduitDTO produitDTO);

   ProduitDTO updateproduit(ProduitDTO produitDTO);

   void deleteProduit(Long id);

   byte[] getPhoto(Long id) throws Exception;

    List<ProduitDTO> produitselect();

   List<ProduitDTO> rechercheParNom(String mc);
}
