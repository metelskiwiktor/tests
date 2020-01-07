package pl.wiktor.shop.tests.model;

import java.math.BigDecimal;
import java.util.Set;

public class Item {
    private int id;
    private String name;
    private BigDecimal basicPrice;
    private Discount discount;
    private Set<Category> categories;
    private Set<Tag> tags;
    private Stock stock;
    private Set<Author> authors;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasicPrice(BigDecimal basicPrice) {
        this.basicPrice = basicPrice;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
