package ma.enset.pfe_ecommerce.web;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Produit;
import ma.enset.pfe_ecommerce.exceptions.ProduitNotFoundException;
import ma.enset.pfe_ecommerce.repositories.ProduitRepository;
import ma.enset.pfe_ecommerce.services.ProduitService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController @AllArgsConstructor
public class ProduitRestControlleur {
    private ProduitService produitService;
    private ProduitRepository produitRepository;


    // Les produits
    @GetMapping(path = "/produits")
    public List<ProduitDTO> produits(){
        return produitService.listprod();
    }

    @GetMapping(path = "/produits/{id}")
    public ProduitDTO getproduit(@PathVariable Long id) throws ProduitNotFoundException {
        return produitService.getproduit(id);
    }

    @PostMapping(path = "/produits")
    public ProduitDTO saveproduit(@RequestBody ProduitDTO produitDTO) {

        return produitService.saveproduit(produitDTO);
    }

    @PutMapping(path = "/produits/{id}")
    public ProduitDTO updateproduit(@PathVariable Long id, @RequestBody ProduitDTO produitDTO) {
        produitDTO.setId(id);
        return produitService.updateproduit(produitDTO);
    }

    @DeleteMapping(path = "/produits/{id}")
    public void deleteproduit(@PathVariable Long id){
        produitService.deleteProduit(id);
    }

    @GetMapping(path="/photoproduit/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getphoto(@PathVariable Long id) throws Exception {
        return produitService.getPhoto(id);
    }

    @GetMapping(path = "/produitSelect")
    public List<ProduitDTO> produitSelect(){
        return produitService.produitselect();
    }

    @GetMapping(path = "/rechercheParNom")
    public List<ProduitDTO> rechercheParNom(@Param("mc") String mc){
        return produitService.rechercheParNom(mc);
    }
}
