
import Utils.HibernateConfig;
import dao.IDao;
import entities.Categorie;
import entities.Commande;
import entities.Produit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.ProduitService;

import java.util.Date;
import java.util.Set;

public class presentation {

    public static void main(String[] args) {
        ApplicationContext context = new
                AnnotationConfigApplicationContext(HibernateConfig.class);
        IDao<Produit> produitService = context.getBean("produitService", ProduitService.class);

        Categorie categorie = Categorie.builder()
                .code("C1")
                .label("C1")
                .build();
        Produit produit = Produit.builder()
                .price(100f)
                .category(categorie)
                .build();
        Commande commande = Commande.builder()
                .date(new Date())
                .produits(Set.of(produit))
                .build();
        produitService.create(produit);
    }
}