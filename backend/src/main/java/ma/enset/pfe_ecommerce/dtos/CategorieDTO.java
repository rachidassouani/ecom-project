package ma.enset.pfe_ecommerce.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.pfe_ecommerce.entites.Produit;


import java.util.List;
@Data
public class CategorieDTO {
    private Long id;
    private String nom;
    private String description;
}
