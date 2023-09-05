package ma.enset.pfe_ecommerce.repositories;

import ma.enset.pfe_ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
