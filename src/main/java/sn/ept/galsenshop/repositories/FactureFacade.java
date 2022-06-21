package sn.ept.galsenshop.repositories;

import sn.ept.galsenshop.entities.Facture;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.util.List;

@Stateless
public class FactureFacade extends AbstractFacade<Facture>{

    @PersistenceContext(unitName = "galsenShopPU")
    private EntityManager em;

    public FactureFacade(Class<Facture> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FactureFacade() {
        super(Facture.class);
    }

    public List<Facture> searchClientFacture(String searchText) {

        String request = "SELECT f FROM Facture f WHERE f.client.nom like '%" + searchText + "%' ORDER BY f.numero DESC";
        Query q = em.createQuery(request);
        return q.getResultList();
    }

    public List<Facture> searchFactureBetweenDate(String startDate, String endDate,int page, int size) throws ParseException {

        String request = "SELECT f FROM Facture f WHERE f.date >= '"+ startDate +"' and f.date<= '"+ endDate+"'";
        Query q = em.createQuery(request);
        q.setFirstResult(page * size);
        q.setMaxResults(size);
        return q.getResultList();
    }

}
