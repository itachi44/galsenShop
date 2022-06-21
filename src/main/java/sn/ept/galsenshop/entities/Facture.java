package sn.ept.galsenshop.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "FACTURE")
@XmlRootElement(name = "")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NUMERO", nullable = false)
    private Integer numero;

    @OneToOne
    private Client client;

    @Column(name="DATE",nullable=false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @PrePersist
    public void saveDateFacture() throws ParseException {
        Date currentDate = new Date();
        SimpleDateFormat DATE_FORMAT= new SimpleDateFormat("yyyy-MM-dd");
        this.date = DATE_FORMAT.parse(DATE_FORMAT.format(currentDate));

    }
    public Facture(int numero, Client id_client, Date date) {
        this.numero = numero;
        this.client = id_client;
        this.date = date;
    }

    public Facture() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}