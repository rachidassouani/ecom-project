package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Produit;
import ma.enset.pfe_ecommerce.exceptions.ProduitNotFoundException;
import ma.enset.pfe_ecommerce.mappers.ProduitMappersImp;
import ma.enset.pfe_ecommerce.repositories.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepository;
    private final ProduitMappersImp produitMappers;


    public ProduitServiceImpl(ProduitRepository produitRepository, ProduitMappersImp produitMappers) {
        this.produitRepository = produitRepository;
        this.produitMappers = produitMappers;
    }

    @Override
    public ProduitDTO getproduit(Long id) throws ProduitNotFoundException {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new ProduitNotFoundException("produit not found"));
        return produitMappers.fromProduit(produit);
    }

    @Override
    public List<ProduitDTO> listprod() {
        List<Produit> produits = produitRepository.findAll();
        List<ProduitDTO> produitDTOS = produits.stream()
                .map(produit -> produitMappers.fromProduit(produit))
                .collect(Collectors.toList());
        return produitDTOS;
    }

    @Override
    public ProduitDTO saveproduit(ProduitDTO produitDTO){
        Produit produit= produitMappers.fromProduitDTO(produitDTO);
        Produit saveProduit = produitRepository.save(produit);
        return produitMappers.fromProduit(saveProduit);
    }

    @Override
    public ProduitDTO updateproduit(ProduitDTO produitDTO){
        Produit produit= produitMappers.fromProduitDTO(produitDTO);
        Produit saveProduit = produitRepository.save(produit);
        return produitMappers.fromProduit(saveProduit);
    }

    @Override
    public void deleteProduit(Long id){
        produitRepository.deleteById(id);
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception{
        Produit produit = produitRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/produits/"+produit.getPhoto()));
    }
    @Override
    public List<ProduitDTO> produitselect(){
        List<Produit> produits = produitRepository.findBySelectionIsTrue();
        List<ProduitDTO> produitDTOS = produits.stream()
                .map(produit -> produitMappers.fromProduit(produit))
                .collect(Collectors.toList());
        return produitDTOS;
    }
    @Override
    public List<ProduitDTO> rechercheParNom(String mc){
        List<Produit> produits = produitRepository.findByNomContains(mc);
        List<ProduitDTO> produitDTOS = produits.stream()
                .map(produit -> produitMappers.fromProduit(produit))
                .collect(Collectors.toList());
        return produitDTOS;
    }
}
