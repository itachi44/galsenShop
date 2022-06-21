package sn.ept.galsenshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "CATEGORIE")
@XmlRootElement(name = "categorie")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODE_CATEGORIE", length = 3)
    private String code_categorie;

    @Column(name = "LIBELLE", length = 100)
    private String libelle;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Column(name = "TVA")
    private Double tva;

    public void setTva(Double tva) {
        this.tva = tva;
    }

    @PrePersist
    public void saveTVA() {
        if(this.tva==null){
            this.tva=0.0;
        }
    }
    public Categorie() {
    }
    public Categorie(String code, String libelle, String description, double tva) {
        this.code_categorie = code;
        this.libelle = libelle;
        this.description = description;
        this.tva = tva;
    }

    public Categorie(String code, String libelle, String description) {
        this.code_categorie = code;
        this.libelle = libelle;
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode_categorie() {
        return code_categorie;
    }

    public void setCode_categorie(String code_categorie) {
        this.code_categorie = code_categorie;
    }

    public Double getTva() {
        return tva;
    }

}