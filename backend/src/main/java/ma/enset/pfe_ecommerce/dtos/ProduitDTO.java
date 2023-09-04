package ma.enset.pfe_ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.pfe_ecommerce.entites.Categorie;

@Data
public class ProduitDTO {
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private boolean promotion;
    private boolean selection;
    private boolean disponible;
    private String photo;
    private CategorieDTO categorie;
}
