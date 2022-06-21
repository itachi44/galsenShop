package sn.ept.galsenshop.resources;


import sn.ept.galsenshop.entities.Categorie;
import sn.ept.galsenshop.repositories.CategorieFacade;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("AjoutCategorieBean")
@ViewScoped
public class AjoutCategorieBean implements Serializable {

    private Categorie categorie=new Categorie();

    @EJB
    private CategorieFacade categorieFacade;

    public AjoutCategorieBean() {
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String enregistrerCategorie(){
        FacesContext context = FacesContext.getCurrentInstance();
        try{
        categorieFacade.create(categorie);
        System.out.println("categorie enregistree "+categorie);
        FacesMessage message = new FacesMessage("Enregistrement reussie.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage("statusMessages", message);
        return "categories.xhtml?faces-redirect=true";
        }catch (Exception e){
            FacesMessage message = new FacesMessage("Quelque chose ne marche pas veuillez reessayer.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage("statusMessages", message);
            return "";
        }
    }

}
