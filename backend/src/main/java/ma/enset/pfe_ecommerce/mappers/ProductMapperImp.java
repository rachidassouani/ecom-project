package ma.enset.pfe_ecommerce.mappers;

import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.dtos.ProductRequest;
import ma.enset.pfe_ecommerce.dtos.ProductResponse;
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
        if (product.getCategory() != null) {
            productDTO.setCategoryDTO(categoryMapper.fromCategory(product.getCategory()));
        }
        return productDTO;
    }

    public ProductResponse fromProductToProductResponse(Product product){
        ProductResponse productResponse = new ProductResponse();
        BeanUtils.copyProperties(product, productResponse);
        if (product.getCategory() != null) {
            productResponse.setCategoryDTO(categoryMapper.fromCategory(product.getCategory()));
        }
        return productResponse;
    }

    public Product fromProductDTO(ProductDTO productDTO){
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        return product;
    }

    public Product fromProductRequest(ProductRequest productRequest) {
        System.out.println("inside product request mapper");
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setAvailable(productRequest.getIsAvailable());
        System.out.println("done from product request mapper");
        return product;
    }
}
