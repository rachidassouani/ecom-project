package ma.enset.pfe_ecommerce;

import ma.enset.pfe_ecommerce.mappers.CategoryMapperImpl;
import ma.enset.pfe_ecommerce.model.Category;
import ma.enset.pfe_ecommerce.model.Product;
import ma.enset.pfe_ecommerce.repositories.CategoryRepository;
import ma.enset.pfe_ecommerce.repositories.ProductRepository;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class PfeEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeEcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository,
                                        CategoryRepository categoryRepository) {
        return args -> {

            categoryRepository.save(new Category(null,"Laptops","Laptops",null));
            categoryRepository.save(new Category(null,"Phones","Phones",null));
            categoryRepository.save(new Category(null,"Watches","Watches",null));

            Random rd = new Random();
            categoryRepository.findAll().forEach(c -> {
                for (int i = 0; i < 10 ; i++) {
                    Product p = new Product();
                    p.setName(RandomString.make(18));
                    p.setPrice(100+rd.nextDouble(100));
                    p.setDescription(RandomString.make(20));
                    p.setAvailable(rd.nextBoolean());
                    p.setPhoto("inconu.png");
                    p.setCategory(c);
                    productRepository.save(p);
                }

            });
        };
    }
}
