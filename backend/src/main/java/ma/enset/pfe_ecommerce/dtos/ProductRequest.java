package ma.enset.pfe_ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;
    private String photo;
    private Long categoryId;


}
