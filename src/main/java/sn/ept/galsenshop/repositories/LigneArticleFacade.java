package sn.ept.galsenshop.repositories;

import sn.ept.galsenshop.entities.Article;
import sn.ept.galsenshop.entities.LigneArticle;
import sn.ept.galsenshop.utils.SalesQty;
import sn.ept.galsenshop.utils.Stat;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Stateless
public class LigneArticleFacade extends AbstractFacade<LigneArticle>{

    @PersistenceContext(unitName = "galsenShopPU")
    private EntityManager em;

    public LigneArticleFacade(Class<LigneArticle> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneArticleFacade() {
        super(LigneArticle.class);
    }

    public List<?> salesQuantityPerArticle (String startDate, String endDate) {

        String request = "SELECT a, count(la.article.code_article) AS qty_per_article FROM LigneArticle la JOIN Article a JOIN Facture f WHERE f.date >= '"+ startDate +"' AND  f.date<= '"+ endDate +"' GROUP BY a";
        Query q = em.createQuery(request);
        List<Object[]> result= q.getResultList();
        List<SalesQty> sales = new ArrayList<>();

        Iterator it = result.iterator();

        while(it.hasNext()){
            Object[] line = (Object[]) it.next();
            SalesQty s = new SalesQty( (Article) line[0],(Long) line[1]);
            sales.add(s);
            System.out.println(sales.get(0));
        }

        return sales;
    }


    public List<Stat> getStockStats () {

        //le stock est calculé en faisant la différence entre le stock initiale et la quantité vendue du produit
        //l'estimation du nombre de jours pour l'épuisement est obtenu par :
        //1. on récupère la quantité moyenne de produits vendus par jour : avg_qty
        //2. on determine le nombre de jours restants grace à la quantité restante : remaining_qty/avg_qty

        String request = "SELECT a.code_article, (a.qte_stock - COUNT(la.article.code_article)), (a.qte_stock - COUNT(la.article.code_article))/AVG((SELECT AVG(la.quantite) FROM LigneArticle la GROUP BY f.date)) FROM LigneArticle la JOIN Article a JOIN Facture f GROUP BY a.code_article";
        Query q = em.createQuery(request);
        List<Object[]> result= q.getResultList();
        List<Stat> stats = new ArrayList<>();

        Iterator it = result.iterator();
        while(it.hasNext()){
            Object[] line = (Object[]) it.next();
            System.out.println(line[0]);
            Stat st = new Stat((String) line[0],(Double) line[1], (int) Math.ceil((Double) line[2]));
            stats.add(st);
            System.out.println(stats.get(0));
        }

        return stats;
    }


}
