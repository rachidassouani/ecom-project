package ma.enset.pfe_ecommerce.services;

import ma.enset.pfe_ecommerce.dtos.ProductDTO;
import ma.enset.pfe_ecommerce.dtos.ProductRequest;
import ma.enset.pfe_ecommerce.dtos.ProductResponse;
import ma.enset.pfe_ecommerce.exceptions.CategoryNotFoundException;
import ma.enset.pfe_ecommerce.model.Category;
import ma.enset.pfe_ecommerce.model.Product;
import ma.enset.pfe_ecommerce.exceptions.ProductNotFoundException;
import ma.enset.pfe_ecommerce.mappers.ProductMapperImp;
import ma.enset.pfe_ecommerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private CategoryService categoryService;
    private final ProductMapperImp productMapperImp;


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductMapperImp productMapperImp) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapperImp = productMapperImp;
    }

    @Override
    public ProductDTO findProductById(Long id) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        return productMapperImp.fromProduct(product);
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> productMapperImp.fromProduct(product))
                .collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) throws CategoryNotFoundException {
        Product productToSave = productMapperImp.fromProductRequest(productRequest);
        Category category = categoryService.findCategoryById(productRequest.getCategoryId());
        if (category != null) {
            productToSave.setCategory(category);
        }
        Product savedProduct = productRepository.save(productToSave);

        return productMapperImp.fromProductToProductResponse(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO){
        Product product = productMapperImp.fromProductDTO(productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapperImp.fromProduct(savedProduct);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    @Override
    public byte[] getPhoto(Long id) throws Exception{
        Product product = productRepository.findById(id).get();
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecom/produits/"+product.getPhoto()));
    }

    /*
    @Override
    public List<ProductDTO> produitselect(){
        List<Produit> produits = produitRepository.findBySelectionIsTrue();
        List<ProductDTO> produitDTOS = produits.stream()
                .map(produit -> produitMappers.fromProduit(produit))
                .collect(Collectors.toList());
        return produitDTOS;
    }
     */

    @Override
    public List<ProductDTO> searchByName(String mc){
        List<Product> products = productRepository.findByNameContains(mc);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> productMapperImp.fromProduct(product))
                .collect(Collectors.toList());
        return productDTOS;
    }

    @Override
    public List<ProductDTO> findAllProductsByCategoryId(Long categoryId) {
        List<Product> productsByCategoryId = productRepository.findAllByCategoryId(categoryId);

        return productsByCategoryId.stream()
                .map(product -> productMapperImp.fromProduct(product))
                .collect(Collectors.toList());
    }
}
