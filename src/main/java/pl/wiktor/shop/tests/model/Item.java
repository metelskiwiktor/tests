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


}
