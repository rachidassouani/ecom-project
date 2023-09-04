package ma.enset.pfe_ecommerce.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.grammars.hql.HqlParser;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String profil;
    @ManyToOne
    private Roles roles;
    @OneToMany(mappedBy = "utilisateurs")
    private List<Commentaires> listCommantaire;

}
