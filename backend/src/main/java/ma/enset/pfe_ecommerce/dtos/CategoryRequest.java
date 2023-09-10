package ma.enset.pfe_ecommerce.dtos;

import lombok.Data;

@Data
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
}
