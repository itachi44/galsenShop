package sn.ept.galsenshop.repositories;

import sn.ept.galsenshop.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ClientFacade extends AbstractFacade<Client>{

    @PersistenceContext(unitName = "galsenShopPU")
    private EntityManager em;

    public ClientFacade(Class<Client> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }


    public Client findClientByPhoneNumber(String searchText) {

        String request = "SELECT c FROM Client c WHERE c.telephone like '%" + searchText + "%'";
        Query q = em.createQuery(request);
        return (Client) q.getSingleResult();
    }

    public List<Client> findClientByName(String searchText,int page, int size) {

        String request = "SELECT c FROM Client c WHERE c.nom like '%" + searchText + "%'";
        Query q = em.createQuery(request);
        q.setFirstResult(page * size);
        q.setMaxResults(size);
        return q.getResultList();
    }
}
