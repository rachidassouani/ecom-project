package ma.enset.pfe_ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private String photo;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;


}
