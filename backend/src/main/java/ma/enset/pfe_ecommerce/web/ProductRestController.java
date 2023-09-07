package ma.enset.pfe_ecommerce.web;

import lombok.AllArgsConstructor;
import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.exceptions.ProductNotFoundException;
import ma.enset.pfe_ecommerce.repositories.ProductRepository;
import ma.enset.pfe_ecommerce.services.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/products")
@CrossOrigin("*")
public class ProductRestController {
    private ProductService productService;
    private ProductRepository productRepository;

    @GetMapping
    public List<ProductDTO> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("{id}")
    public ProductDTO findProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productService.findProductById(id);
    }

    @PostMapping
    public ProductDTO saveProduct(@RequestBody ProductDTO productDTO) {
        return productService.saveProduct(productDTO);
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
