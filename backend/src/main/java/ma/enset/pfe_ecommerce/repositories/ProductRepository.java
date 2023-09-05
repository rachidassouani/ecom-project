package ma.enset.pfe_ecommerce.repositories;

import ma.enset.pfe_ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    //List<Product> findBySelectionIsTrue();
    List<Product> findByNameContains(String mc);
}
