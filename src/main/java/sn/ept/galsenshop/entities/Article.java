package sn.ept.galsenshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "ARTICLE")
@XmlRootElement(name = "article")
public class Article {
    @Override
    public String toString() {
        return "Article{" +
                "code_article='" + code_article + '\'' +
                ", categorie=" + categorie +
                ", libelle='" + libelle + '\'' +
                ", description='" + description + '\'' +
                ", prix_unitaire=" + prix_unitaire +
                ", unite='" + unite + '\'' +
                ", qte_stock=" + qte_stock +
                '}';
    }

    @Id
    private String code_article;

    @ManyToOne
    private Categorie categorie;

    @Column(name = "LIBELLE", length = 100)
    private String libelle;

    @Column(name = "DECRIPTION", length = 300)
    private String description;

    @Column(name = "PRIX_UNITAIRE")
    private double prix_unitaire;

    @Column(name = "UNITE", length = 45)
    private String unite;

    @Column(name = "QTE_STOCK")
    private double qte_stock;

    public Article() {
    }

    public Article(String code,Categorie categorie, String libelle, String description, double prix_unitaire, String unite, double qte_stock) {
        this.code_article = code;
        this.categorie = categorie;
        this.libelle = libelle;
        this.description = description;
        this.prix_unitaire = prix_unitaire;
        this.unite = unite;
        this.qte_stock = qte_stock;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public double getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(double prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public double getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(double qte_stock) {
        this.qte_stock = qte_stock;
    }

    public String getCode_article() {
        return code_article;
    }

    public void setCode_article(String code_article) {
        this.code_article = code_article;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return code_article.equals(article.code_article) && categorie.equals(article.categorie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code_article, categorie);
    }
}