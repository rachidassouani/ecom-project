package ma.enset.pfe_ecommerce.repositories;

import ma.enset.pfe_ecommerce.dtos.ProduitDTO;
import ma.enset.pfe_ecommerce.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@CrossOrigin("*")
//@RepositoryRestResource
public interface ProduitRepository extends JpaRepository<Produit,Long> {
    //@RestResource(path="/produitSelect")
    List<Produit> findBySelectionIsTrue();


    List<Produit> findByNomContains(String mc);
    //@Query("select p from Produit p where p.nom like :X ")
    //List<Produit> chercher(@Param("x") String keyword);
}
