package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.exceptions.ProductNotFoundException;


import java.util.List;


public interface ProductService {
   ProductDTO findProductById(Long id) throws ProductNotFoundException;
   List<ProductDTO> findAllProducts();
   ProductDTO saveProduct(ProductDTO productDTO);
   ProductDTO updateProduct(ProductDTO productDTO);
   void deleteProduct(Long id);
   byte[] getPhoto(Long id) throws Exception;
   List<ProductDTO> searchByName(String mc);
}
