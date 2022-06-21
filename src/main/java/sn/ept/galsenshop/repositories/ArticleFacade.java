package sn.ept.galsenshop.repositories;

import sn.ept.galsenshop.entities.Article;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ArticleFacade extends AbstractFacade<Article>{

    @PersistenceContext(unitName = "galsenShopPU")
    private EntityManager em;

    public ArticleFacade(Class<Article> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }


    public List<Article> findArticle(String searchText,int page, int size) {

        String request1 = "SELECT a FROM Article a WHERE a.libelle like '%" + searchText + "%' ORDER BY a.prix_unitaire DESC";
        String request2 = "SELECT a FROM Article a WHERE a.code_categorie.description like '%" + searchText + "%' ORDER BY a.prix_unitaire DESC";
        String request3 = "SELECT a FROM Article a WHERE a.description like '%" + searchText + "%' ORDER BY a.prix_unitaire DESC";

        Query q = em.createQuery(request1);

        if(q.getResultList().isEmpty()){
            q = em.createQuery(request2);
            if(q.getResultList().isEmpty()){
                q = em.createQuery(request3);
            }
        }

        q.setFirstResult(page * size);
        q.setMaxResults(size);
        return (List<Article>) q.getResultList();
    }
}
