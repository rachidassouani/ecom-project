package ma.enset.pfe_ecommerce.dtos;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private String photo;
    private CategoryResponse categoryDTO;
}
