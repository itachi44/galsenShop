package sn.ept.galsenshop.utils;

public class Stat {

    private String code_article;

    private Double stock;

    private Integer jours_restants;

    public String getCode_article() {
        return code_article;
    }

    public void setCode_article(String code_article) {
        this.code_article = code_article;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Integer getJours_restants() {
        return jours_restants;
    }

    public void setJours_restants(Integer jours_restants) {
        this.jours_restants = jours_restants;
    }

    public Stat() {
    }

    public Stat(String code_article, Double stock, Integer jours_restants) {
        this.code_article = code_article;
        this.stock = stock;
        this.jours_restants = jours_restants;
    }

    @Override
    public String toString() {
        return "Stat{" +
                "code_article='" + code_article + '\'' +
                ", stock=" + stock +
                ", jours_restants=" + jours_restants +
                '}';
    }
}
