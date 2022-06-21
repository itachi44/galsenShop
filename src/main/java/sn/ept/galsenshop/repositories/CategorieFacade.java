package sn.ept.galsenshop.repositories;

import sn.ept.galsenshop.entities.Categorie;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CategorieFacade extends AbstractFacade<Categorie>{

    @PersistenceContext(unitName = "galsenShopPU")
    private EntityManager em;

    public CategorieFacade(Class<Categorie> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieFacade() {
        super(Categorie.class);
    }

    public Categorie searchCategory(String searchText) {

        String request1 = "SELECT c FROM Categorie c WHERE c.libelle like '%" + searchText + "%' ORDER BY c.code_categorie DESC";
        String request2 = "SELECT c FROM Categorie c WHERE c.code_categorie like '%" + searchText + "%' ORDER BY c.code_categorie DESC";

        Query q = em.createQuery(request1);
        if(q.getResultList().isEmpty()){
            q = em.createQuery(request2);
        }
        return (Categorie) q.getSingleResult();
    }
}
