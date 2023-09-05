package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.model.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProductMapperImp {

    private final CategoryMapperImpl categoryMapper;

    public ProductMapperImp(CategoryMapperImpl categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);
        productDTO.setCategoryDTO(categoryMapper.fromCategory(product.getCategory()));
        return productDTO;
    }

    public Product fromProductDTO(ProductDTO produitDTO){
        Product product = new Product();
        BeanUtils.copyProperties(produitDTO, product);
        return product;
    }
}
