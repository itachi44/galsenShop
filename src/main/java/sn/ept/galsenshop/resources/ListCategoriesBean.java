package sn.ept.galsenshop.resources;

import sn.ept.galsenshop.entities.Categorie;
import sn.ept.galsenshop.repositories.CategorieFacade;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named(value = "ListCategoriesBean")
@RequestScoped
public class ListCategoriesBean {

    @EJB
    private CategorieFacade categorieFacade;

    private List<Categorie> categories;

    public ListCategoriesBean() {
    }

    public List<Categorie> getCategories() {
        if (categories==null) {
            categories=categorieFacade.findAll();
        }
        return categories;
    }

    public void setCategories(List<Categorie> categories) {

        this.categories = categories;
    }

}
