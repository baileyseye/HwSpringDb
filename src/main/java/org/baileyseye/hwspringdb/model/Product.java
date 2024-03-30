package org.baileyseye.hwspringdb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "\"Product\"", schema = "shop")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Integer id;

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    @JoinColumn(name = "categories", nullable = false)
    private Category categories;

    @Column(name = "product_price", precision = 18, scale = 2)
    private BigDecimal productPrice;

    @ManyToMany
    @JoinTable(name = "author_product_id",
            joinColumns = @JoinColumn(name = "author_product_id"),
            inverseJoinColumns = @JoinColumn(name = "author_name_id"))
    private Set<Author> authors = new LinkedHashSet<>();

}