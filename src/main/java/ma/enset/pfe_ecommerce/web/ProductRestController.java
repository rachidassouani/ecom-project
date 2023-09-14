package ma.enset.pfe_ecommerce.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.dtos.ProductRequest;
import ma.enset.pfe_ecommerce.dtos.ProductResponse;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.exceptions.ProductNotFoundException;
import ma.enset.pfe_ecommerce.model.Product;
import ma.enset.pfe_ecommerce.repositories.ProductRepository;
import ma.enset.pfe_ecommerce.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
@CrossOrigin("*")
public class ProductRestController {
    private ProductService productService;
    private ProductRepository productRepository;
    private ObjectMapper mapper;


    @GetMapping
    public List<ProductDTO> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("categories/{categoryId}")
    public List<ProductDTO> findAllProductsByCategoryId(@PathVariable Long categoryId){
        return productService.findAllProductsByCategoryId(categoryId);
    }

    @GetMapping("{id}")
    public ProductDTO findProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productService.findProductById(id);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(
            @RequestParam("productRequest") String model,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) throws CategoryNotFoundException, JsonProcessingException {
        ProductRequest productRequest = mapper.readValue(model, ProductRequest.class);
        ProductResponse savedProduct = productService.saveProduct(productRequest, image);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        productDTO.setId(id);
        return productService.updateProduct(productDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping(path="/images/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhoto(@PathVariable Long id) throws Exception {
        return productService.getPhoto(id);
    }

    /*
        @GetMapping(path = "/produitSelect")
        public List<ProductDTO> produitSelect(){
            return productService.produitselect();
        }
     */

    @GetMapping(path = "/searchByName")
    public List<ProductDTO> searchByName(@Param("mc") String mc){
        return productService.searchByName(mc);
    }
}
