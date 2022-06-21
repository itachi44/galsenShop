package sn.ept.galsenshop.configs;


import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import sn.ept.galsenshop.entities.Categorie;
import sn.ept.galsenshop.repositories.CategorieFacade;
import java.sql.Connection;
import java.sql.SQLException;
import static java.lang.System.out;

@Singleton
@Startup
@LocalBean
public class initCategorie {

    @Resource(name = "jdbc/GalsenShopPool")
    private DataSource dataSource;

    @EJB
    private CategorieFacade categorieFacade;

    @PostConstruct
    public void initialize() {
        out.println("initializing categories...");
        Categorie c1 = new Categorie("BSE","biens sociaux essentiels","les médicaments,appareils médico-chirurgicaux, le papier journal, les livres, les journaux, les fauteuils roulants, certains engrais");
        Categorie c2 = new Categorie("BPN","biens de première nécessité","les matières premières de base, les biens d’équipement et les intrants spécifiques.",5);
        Categorie c3 = new Categorie("IPI","intrants et produits intermédiaires","les médicaments,appareils médico-chirurgicaux, le papier journal, les livres, les journaux, les fauteuils roulants, certains engrais",10);
        Categorie c4 = new Categorie("BCF","biens de consommation finale","catégorie par defaut",20);

        categorieFacade.create(c1);
        categorieFacade.create(c2);
        categorieFacade.create(c3);
        categorieFacade.create(c4);

    }
}
