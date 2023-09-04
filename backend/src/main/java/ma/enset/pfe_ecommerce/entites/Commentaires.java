package ma.enset.pfe_ecommerce.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Commentaires {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String msg;
    private int rank;
    @ManyToOne
    private Utilisateurs utilisateurs;
    @ManyToOne
    private Produit produit;
}
