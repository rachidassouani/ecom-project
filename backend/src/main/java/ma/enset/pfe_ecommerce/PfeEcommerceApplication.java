package ma.enset.pfe_ecommerce;

import ma.enset.pfe_ecommerce.entites.Categorie;
import ma.enset.pfe_ecommerce.entites.Produit;
import ma.enset.pfe_ecommerce.repositories.CategorieRepository;
import ma.enset.pfe_ecommerce.repositories.ProduitRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@SpringBootApplication
public class PfeEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PfeEcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProduitRepository produitRepository,
                                        CategorieRepository categorieRepository) {
        return args -> {

            categorieRepository.save(new Categorie(null,"Cat1","categorie1",null ));
            categorieRepository.save(new Categorie(null,"Cat2","categorie2",null ));
            categorieRepository.save(new Categorie(null,"Cat3","categorie3",null ));
            Random rd=new Random();
            categorieRepository.findAll().forEach(c -> {
                for (int i = 0; i <10 ; i++) {
                    Produit p=new Produit();
                    p.setNom(RandomString.make(18));
                    p.setPrix(100+rd.nextDouble(100));
                    p.setDisponible(rd.nextBoolean());
                    p.setPromotion(rd.nextBoolean());
                    p.setSelection(rd.nextBoolean());
                    p.setPhoto("inconu.png");
                    p.setCategorie(c);
                    produitRepository.save(p);
                }

            });




        };
    }
}
