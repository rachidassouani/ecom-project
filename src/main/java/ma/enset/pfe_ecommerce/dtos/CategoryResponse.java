package ma.enset.pfe_ecommerce.dtos;


import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
