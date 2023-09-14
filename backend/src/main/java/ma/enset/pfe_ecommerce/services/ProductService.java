package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.dtos.ProductRequest;
import ma.enset.pfe_ecommerce.dtos.ProductResponse;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.exceptions.ProductNotFoundException;
import ma.enset.pfe_ecommerce.model.Product;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


public interface ProductService {
   ProductDTO findProductById(Long id) throws ProductNotFoundException;
   List<ProductDTO> findAllProducts();
   ProductResponse saveProduct(ProductRequest productRequest,  MultipartFile image) throws CategoryNotFoundException;
   ProductDTO updateProduct(ProductDTO productDTO);
   void deleteProduct(Long id);
   byte[] getPhoto(Long id) throws Exception;
   List<ProductDTO> searchByName(String mc);
   List<ProductDTO> findAllProductsByCategoryId(Long categoryId);
}
