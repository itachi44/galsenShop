package sn.ept.galsenshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "LIGNE_ARTICLE")
@XmlRootElement(name = "ligne_article")
public class LigneArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    private Article article;

    @ManyToOne
    private Facture numero_facture;

    @Column(name = "QUANTITE", nullable = false)
    private double quantite;

    public LigneArticle(Article article, Facture numero_facture, double quantite) {
        this.article = article;
        this.numero_facture = numero_facture;
        this.quantite = quantite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LigneArticle() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Facture getNumero_facture() {
        return numero_facture;
    }

    public void setNumero_facture(Facture numero_facture) {
        this.numero_facture = numero_facture;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LigneArticle that = (LigneArticle) o;
        return numero_facture == that.numero_facture && article.equals(that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, numero_facture);
    }
}