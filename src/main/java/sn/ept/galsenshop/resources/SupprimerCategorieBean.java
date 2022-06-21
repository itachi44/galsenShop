package sn.ept.galsenshop.resources;

import sn.ept.galsenshop.entities.Categorie;
import sn.ept.galsenshop.repositories.CategorieFacade;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("SupprimerCategorieBean")
@ViewScoped
public class SupprimerCategorieBean implements Serializable {


    @EJB
    private CategorieFacade categorieFacade;

    public SupprimerCategorieBean() {
    }

    public String supprimer(Categorie categorie){

        categorieFacade.remove(categorie);

        return "categories.xhtml?faces-redirect=true";
    }

}
