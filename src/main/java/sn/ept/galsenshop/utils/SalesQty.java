package sn.ept.galsenshop.utils;

import sn.ept.galsenshop.entities.Article;

public class SalesQty {

    private Article article;
    private Long qty;

    public SalesQty() {
    }

    @Override
    public String toString() {
        return "SalesQty{" +
                "article=" + article +
                ", qty=" + qty +
                '}';
    }

    public SalesQty(Article article, Long qty) {
        this.article = article;
        this.qty = qty;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }
}
