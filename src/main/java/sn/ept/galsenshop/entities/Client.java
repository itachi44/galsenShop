package sn.ept.galsenshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CLIENT")
@XmlRootElement(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOM", length = 45)
    private String nom;

    @Column(name = "ADDRESSE", length = 45)
    private String addresse;

    @Column(name = "TELEPHONE", length = 45, unique = true)
    private String telephone;

    public Client(Integer id, String nom, String addresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.addresse = addresse;
        this.telephone = telephone;
    }

    public Client() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}